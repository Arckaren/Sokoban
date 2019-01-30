import java.util.Arrays;

/**
 * ArrayList
 */
public class ArrayList implements List {
    public final static int DEFAULT_SIZE = 2;
    private Integer[] tab = new Integer[DEFAULT_SIZE];
    /**
     * effective number of elements
     */
    private int size = 0;

    // on met begin à 1 car la taille est égale à 0 au début
    private int begin = 0;

    @Override
    public void addHead(int element) {
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
    public void addTail(int element) {
        if (!canAdd()) {
            growTab();
        }
        tab[end()] = element;
        size++;
    }

    private void growTab() {
        Integer tabTmp[] = Arrays.copyOfRange(tab, begin, tab.length * 2 + begin - 1);
        System.arraycopy(tab, 0, tabTmp, tab.length - begin, begin);
        tab = tabTmp;
        begin = 0;
    }

    @Override
    public int popHead() {
        if (!isEmpty()) {
            int tmpBegin = begin;
            begin = modAccess(begin + 1);
            size--;
            return tab[tmpBegin];
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

    @Override
    public int get(int pos) {
        return tab[modAccess(pos + begin)];
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
}