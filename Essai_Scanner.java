import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.IllegalArgumentException;

/**
 * Personne
 */
class Personne {
    private static int nb = 0;
    private int age;
    private String nom;

    public Personne(String nom, int age) {
        this.nom = nom;
        try {
            setAge(age);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            // throw e;
            setAge(1);
        }
        nb++;
    }

    /**
     * @param age the age to set
     */
    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age ne peut etre inferieur à 0 !");
        } else {
            this.age = age;
        }
    }

    public int getAge() {
        return age;
    }

    /**
     * @return the nb
     */
    public static int getNb() {
        return nb;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

}

class Essai_Scanner {
    public static void main(String[] args) {
        Scanner my_scanner;
        String ligne;
        int i;
        boolean cond = false;
        my_scanner = new Scanner(System.in);
        while (!cond) {
            System.out.println("Saisissez une ligne");
            try {
                ligne = my_scanner.nextLine();
                System.out.println("Vous avez saisi la ligne : " + ligne);
                System.out.println("Saisissez un entier:");
                i = my_scanner.nextInt();
                System.out.println("entier entré : " + i);
                cond = true;
            } catch (InputMismatchException e) {
                System.err.println("Entier invalide !!!");
            } catch (NoSuchElementException e) {
                System.err.println("N'écrivez pas de ligne vide !!!");
            }
        }

        // Personne pers = new Personne("michel", 0);
        // Personne pers2 = new Personne("jean-paul", 1);

        // System.out.println("age de la personne : " + pers.getAge());
        // System.out.println("nombre de personnes : " + Personne.getNb());
        // System.out.println("nom de la personne pers2 : " + pers2.getNom());
        my_scanner.close();
    }
}