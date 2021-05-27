import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class CittaNodo {
    Coordinata posizione;
    String nome;
    int id;
    ArrayList<Integer> idCittaCollegate;


    public ArrayList<Integer> getIdCittaCollegate() {
        return idCittaCollegate;
    }

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
        this.idCittaCollegate = new ArrayList<>();
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
        this.idCittaCollegate = new ArrayList<>();
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
     * @param listaCitta Arraylist contenente tutte le città già inserite dalla lettura dell'xml
     * @param id     id della città di cui si vuole verificare la presenza
     * @return CittaNodo: torna la città se presente, oppure una città con id = -1
     */
    public static CittaNodo trovaCittaDaID(ArrayList<CittaNodo> listaCitta, int id){
        for(CittaNodo citta : listaCitta) {
            if(citta.getId() == id) {
                return citta;
            }
        }
        return new CittaNodo();
    }

    /**
     * metodo che consente di ottenere una città presente nella mappa, dato il suo id, nel caso in cui non fosse presente
     * ritorna una città "vuota" che ha id = -1
     * @param mappa
     * @param id  della città da cercare
     * @return  la città desiderata
     */

    public static CittaNodo trovaCittaInMappaId(Mappa mappa, int id){
        Set<CittaNodo> keys = mappa.getMappa().keySet();
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
