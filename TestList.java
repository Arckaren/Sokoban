import structures.ArrayList;
import structures.ArrayListFactory;
import structures.LinkedListFactory;
import structures.List;
import structures.ListFactory;

/**
 * TestList
 */
public class TestList {
    public static void doAssert(boolean cond, String msg) {
        if (!cond) {
            System.err.println(msg);
        }
    }

    public static void test(ListFactory listFact, int nbElemMax) {
        List<Integer> list = listFact.create();
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
        list.addTail(4);
        list.addTail(6);
        list.addTail(9);
        list.addHead(10);
        System.out.println("List:");
        System.out.println(list);
        doAssert(list.popHead() == 10, "La tête est incorrecte");
        doAssert(list.popHead() == 7, "La tête est incorrecte");
        doAssert(list.popHead() == 3, "La tête est incorrecte");
        doAssert(list.popHead() == 4, "La tête est incorrecte");
        doAssert(list.popHead() == 6, "La tête est incorrecte");
        doAssert(list.popHead() == 9, "La tête est incorrecte");
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
        list.addHead(2);
        list.addHead(10);
        list.addHead(33);
        list.addHead(1);
        System.out.println("List:");
        System.out.println(list);
        doAssert(list.popHead() == 1, "La tête est incorrecte");
        doAssert(list.popHead() == 33, "La tête est incorrecte");
        doAssert(list.popHead() == 10, "La tête est incorrecte");
        doAssert(list.popHead() == 2, "La tête est incorrecte");
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
            list.addHead(7 + i);
        }
        System.out.println("List:");
        System.out.println(list);
        for (int i = 0; i < nbElemMax; i++) {
            list.popHead();
        }
        try {
            for (int i = 0; i < nbElemMax + 10; i++) {
                list.addHead(7 + i);
            }
        } catch (Exception e) {
            System.out.println("Max exceeded with exception thrown");
        }
        System.out.println("List:");
        System.out.println(list);
    }

    public static void main(String[] args) {
        System.out.println("Debut du test de la liste chainée");
        ListFactory linkedList = new LinkedListFactory();
        test(linkedList, 100);
        System.out.println("Fin du test de la liste chainée");
        System.out.println("Debut du test de la liste tableau");
        ListFactory arrayList = new ArrayListFactory();
        test(arrayList, ArrayList.DEFAULT_SIZE);
        System.out.println("Fin du test de la liste tableau");
    }
}