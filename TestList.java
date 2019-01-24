
/**
 * TestList
 */
public class TestList {
    public static void doAssert(boolean cond, String msg) {
        if (!cond) {
            System.err.println(msg);
        }
    }

    public static void test(List list, int nbElemMax) {
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
        list.addTail(3);
        list.addHead(7);
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
        for (int i = 0; i < nbElemMax; i++) {
            list.addHead(7);
        }
        System.out.println("List:");
        System.out.println(list);
        for (int i = 0; i < nbElemMax; i++) {
            list.popHead();
        }
        try {
            for (int i = 0; i < nbElemMax + 1; i++) {
                list.addHead(7);
            }
        } catch (Exception e) {
            System.out.println("Max exceeded with exception thrown");
        }
        System.out.println("List:");
        System.out.println(list);
    }

    public static void main(String[] args) {
        System.out.println("Debut du test de la liste chainée");
        List linkedList = new LinkedList();
        test(linkedList, 100);
        System.out.println("Fin du test de la liste chainée");
        System.out.println("Debut du test de la liste tableau");
        List arrayList = new ArrayList();
        test(arrayList, ArrayList.DEFAULT_SIZE);
        System.out.println("Fin du test de la liste tableau");
    }
}