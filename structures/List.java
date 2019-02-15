package structures;

public interface List<Type> extends Iterable<Type> {
    // insère l'élément element en début de séquence (en première position)
    void addHead(Type element);

    // insère l'élément element en fin de séquence (en dernière position)
    void addTail(Type element);

    // extrait et renvoie la valeur de l'élément situé en début de séquence (en
    // première position)
    Type popHead();

    // renvoie vrai si et seulement si la séquence est vide
    boolean isEmpty();

    Type get(int pos);
}
