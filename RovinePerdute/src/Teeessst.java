import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Teeessst {
    public static void main(String[] args) throws XMLStreamException {
        /*ArrayList<CittaNodo> lista = LetturaDati.leggiCitta();
        for (CittaNodo citta: lista) {
            System.out.println(citta.getNome());
        }*/

        Mappa mappaCitta = new Mappa();

        Set<CittaNodo> settinoCitta = mappaCitta.getMappa().keySet();
        for(CittaNodo key : settinoCitta){
            System.out.println("citta partenza"+ key.getNome());
            for(CittaNodo collegata : mappaCitta.getMappa().get(key)){
                int id = collegata.getId();
                System.out.println(collegata.getNome() + id);

            }
        }
    }
}
