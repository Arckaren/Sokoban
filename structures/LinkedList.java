package structures;

import java.util.Iterator;

/**
 * Cell
 */
class Cell<Type> {
    private Type data;
    private Cell<Type> next;

    Cell(Type data) {
        this.data = data;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Cell<Type> next) {
        this.next = next;
    }

    /**
     * @return the next
     */
    public Cell<Type> getNext() {
        return next;
    }

    /**
     * @return the data
     */
    public Type getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.valueOf(getData());
    }

}

/**
 * LinkedList
 */
public class LinkedList<Type> implements List<Type> {
    private Cell<Type> head;

    LinkedList() {
        head = null;
    }

    @Override
    public void addHead(Type element) {
        Cell<Type> newHead = new Cell<>(element);
        newHead.setNext(head);
        head = newHead;
    }

    @Override
    public void addTail(Type element) {
        if (!isEmpty()) {
            getTail().setNext(new Cell<>(element));
        } else {
            addHead(element);
        }
    }

    @Override
    public Type popHead() {
        if (head != null) {
            Cell<Type> oldHead = head;
            head = head.getNext();
            return oldHead.getData();
        } else {
            throw new RuntimeException("Séquence vide");
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Type get(int pos) {
        Cell<Type> tmp = head;
        for (int i = 0; i < pos; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getData();
    }

    @Override
    public String toString() {
        String listStr = "[";
        Cell<Type> tmp = head;
        while (tmp != null) {
            listStr += tmp;
            if (tmp.getNext() != null) {
                listStr += ", ";
            }
            tmp = tmp.getNext();
        }
        listStr += "]";
        return listStr;
    }

    private Cell<Type> getTail() {
        if (isEmpty()) {
            return null;
        } else {
            Cell<Type> tmp;
            tmp = head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            return tmp;
        }
    }

    @Override
    public Iterator<Type> iterator() {
        return new LinkedListIterator<Type>(this);
    }

    /**
     * @return the head
     */
    protected Cell<Type> getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    protected void setHead(Cell<Type> head) {
        this.head = head;
    }

}