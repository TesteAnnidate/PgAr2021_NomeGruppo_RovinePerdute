import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class CittaNodo {
    Coordinata posizione;
    String nome;
    int id;

    /**
     * costruttore che prende come parametro un oggetto Coordinata
     * @param c1 coordinata
     * @param nome
     * @param id
     */
    public CittaNodo(Coordinata c1, String nome, int id){
        this.posizione = c1;
        this.nome = nome;
        this.id = id;
    }

    /**
     * costruttore che riceve come parametro le singole coordinate e richiama il costruttore di Coordinata al suo interno
     * @param h
     * @param y
     * @param x
     * @param nome
     * @param id
     */
    public CittaNodo(int h, int y, int x, String nome, int id){
        this.posizione = new Coordinata(h, y, x);
        this.nome = nome;
        this.id = id;
    }

    /**
     * costruttore senza parametri che inizializza solo l'id a -1
     * @see "trovaCittaDaID"
     */
    public CittaNodo(){
        this.id = -1;
    }

    /**
     * metodo che verifica la presenza di una città nella mappa, dato il suo id
     *
     * @param mappa hashmap contenente tutte le città già inserte dalla lettura dell'xml
     * @param id     id della città di cui si vuole verificare la presenza
     * @return boolean: torna vero se è presente e falso altrimenti
     */
    public static CittaNodo trovaCittaDaID(HashMap<CittaNodo, ArrayList<CittaNodo>> mappa, int id){
        Set<CittaNodo> keys = mappa.keySet();
        for(CittaNodo chiave : keys) {
            if(chiave.getId() == id) {
                return chiave;
            }
        }
        return new CittaNodo();
    }

    //GETTERS E SETTERS

    public void setPosizione(Coordinata posizione) {
        this.posizione = posizione;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinata getPosizione() {
        return posizione;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
