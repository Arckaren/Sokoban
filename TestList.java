
/**
 * TestList
 */
public class TestList {
    public static void doAssert(boolean cond, String msg) {
        if (!cond) {
            System.err.println(msg);
        }
    }

    public static void test(List list) {
        System.out.println("List:");
        System.out.println(list);
        doAssert(list.isEmpty(), "La liste n'est pas vide");
        try {
            list.popHead();
            System.err.println("popHead est censée lever une exception car la liste est vide");
        } catch (RuntimeException e) {
        }
        list.addHead(7);
        list.addTail(3);
        System.out.println("List:");
        System.out.println(list);
        doAssert(list.popHead() == 7, "La tête est incorrecte");
        doAssert(list.popHead() == 3, "La tête est incorrecte");
        doAssert(list.isEmpty(), "La liste n'est pas vide");
        System.out.println("List:");
        System.out.println(list);
        try {
            list.popHead();
            System.err.println("popHead est censée lever une exception car la liste est vide");
        } catch (RuntimeException e) {
        }

    }

    public static void main(String[] args) {
        List linkedList = new LinkedList();
        test(linkedList);
    }
}