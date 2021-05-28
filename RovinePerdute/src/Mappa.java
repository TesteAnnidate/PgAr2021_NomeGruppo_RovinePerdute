import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mappa {
	private HashMap<CittaNodo, ArrayList<CittaNodo>> mappa;

	/**
	 * costruttore che crea una mappa partendo dai dati letti da un xml
	 */
	public Mappa() throws XMLStreamException {
		ArrayList<CittaNodo> listaCitta = LetturaDati.leggiCitta();
		HashMap<CittaNodo, ArrayList<CittaNodo>> mappaCitta = new HashMap<>();

		for (CittaNodo citta : listaCitta) {
			mappaCitta.put(citta, new ArrayList<CittaNodo>());
			for (Integer idCollegate : citta.getIdCittaCollegate()) {
				// aggiunge all'Arraylist (valore corrispondente a città) la città a cui la
				// chiave è collegata
				mappaCitta.get(citta).add(CittaNodo.trovaCittaDaID(listaCitta, idCollegate));
			}
		}
		this.mappa = mappaCitta;
	}


	// Get
	public HashMap<CittaNodo, ArrayList<CittaNodo>> getMappa() {
		return mappa;
	}
//metodo che calcola il percorso: ha come parametro il percorso
}
