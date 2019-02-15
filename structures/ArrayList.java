package structures;

import java.util.Arrays;
import java.util.Iterator;

/**
 * ArrayList
 */
public class ArrayList<Type> implements List<Type> {
    public final static int DEFAULT_SIZE = 2;
    private Object[] tab = new Object[DEFAULT_SIZE];
    /**
     * effective number of elements
     */
    private int size = 0;

    // on met begin à 1 car la taille est égale à 0 au début
    private int begin = 0;

    @Override
    public void addHead(Object element) {
        if (!canAdd()) {
            growTab();
        }
        if (!isEmpty()) {
            begin = modAccess(begin - 1);
        }
        tab[begin] = element;
        size++;
    }

    @Override
    public void addTail(Object element) {
        if (!canAdd()) {
            growTab();
        }
        tab[end()] = element;
        size++;
    }

    private void growTab() {
        Object tabTmp[] = Arrays.copyOfRange(tab, begin, tab.length * 2 + begin - 1);
        System.arraycopy(tab, 0, tabTmp, tab.length - begin, begin);
        tab = tabTmp;
        begin = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Type popHead() {
        if (!isEmpty()) {
            int tmpBegin = begin;
            begin = modAccess(begin + 1);
            size--;
            return (Type) tab[tmpBegin];
        } else {
            throw new RuntimeException("La liste est vide");
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean canAdd() {
        return size < tab.length;
    }

    private int end() {
        return modAccess(begin + size);
    }

    private int modAccess(int pos) {
        if (pos < 0) {
            return tab.length + (pos % tab.length);
        } else {
            return pos % tab.length;
        }
    }

    protected void remove(int pos) {
        for (int i = pos; i < getSize() - 1; i++) {
            this.set(i, get(i + 1));
        }
        size--;
    }

    private void checkPos(int pos) {
        if (!(pos >= 0 && pos < getSize())) {
            throw new IndexOutOfBoundsException("index " + pos + " not in bounds");
        }
    }

    /**
     * get la valeur à la position pos
     * 
     * as if return "tab[pos]"
     */
    @Override
    @SuppressWarnings("unchecked")
    public Type get(int pos) {
        checkPos(pos);
        return (Type) tab[modAccess(pos + begin)];
    }

    /**
     * set la valeur val à l'indice pos
     * 
     * as if "tab[pos]=val"
     */
    protected void set(int pos, Type val) {
        checkPos(pos);
        tab[modAccess(pos + begin)] = val;
    }

    @Override
    public String toString() {
        String listStr = "[";
        for (int i = 0; i < size; i++) {
            listStr += get(i);
            if (i < size - 1) {
                listStr += ", ";
            }
        }
        listStr += "]";
        return listStr;
    }

    protected int getSize() {
        return size;
    }

    @Override
    public Iterator<Type> iterator() {
        return new ArrayListIterator<Type>(this);
    }
}