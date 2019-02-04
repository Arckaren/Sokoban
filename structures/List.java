package structures;

public interface List {
    // insère l'élément element en début de séquence (en première position)
    void addHead(int element);

    // insère l'élément element en fin de séquence (en dernière position)
    void addTail(int element);

    // extrait et renvoie la valeur de l'élément situé en début de séquence (en
    // première position)
    int popHead();

    // renvoie vrai si et seulement si la séquence est vide
    boolean isEmpty();

    int get(int pos);
}
