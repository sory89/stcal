import java.util.ArrayList;

public class Liste {

    protected ArrayList<Object> liste = new ArrayList<Object>();

    /** Initialise la liste et la laisse vide */
    public Liste(){

    }

    /** Initialise la liste avec la personne en parametre */
    public Liste(Object personne){
        liste.add(personne);
    }

    /** ajoute une personne à la liste */
    public void addPersonne(Object personne){
        liste.add(personne);
    }

    /** supprime une personne de la liste */
    public boolean rmPersonne(Object personne){
        return liste.remove(personne);
    }
}
