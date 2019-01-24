
/**
 * Cell
 */
class Cell {
    private int data;
    private Cell next;

    Cell(int data) {
        this.data = data;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Cell next) {
        this.next = next;
    }

    /**
     * @return the next
     */
    public Cell getNext() {
        return next;
    }

    /**
     * @return the data
     */
    public int getData() {
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
public class LinkedList implements List {
    private Cell head;

    LinkedList() {
        head = null;
    }

    @Override
    public void addHead(int element) {
        Cell newHead = new Cell(element);
        newHead.setNext(head);
        head = newHead;
    }

    @Override
    public void addTail(int element) {
        if (!isEmpty()) {
            getTail().setNext(new Cell(element));
        } else {
            addHead(element);
        }
    }

    @Override
    public int popHead() {
        if (head != null) {
            Cell oldHead = head;
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
    public int get(int pos) {
        Cell tmp = head;
        for (int i = 0; i < pos; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getData();
    }

    @Override
    public String toString() {
        String listStr = "[";
        Cell tmp = head;
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

    private Cell getTail() {
        if (isEmpty()) {
            return null;
        } else {
            Cell tmp;
            tmp = head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            return tmp;
        }
    }

}