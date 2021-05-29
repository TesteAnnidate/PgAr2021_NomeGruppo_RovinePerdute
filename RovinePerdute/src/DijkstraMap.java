import java.util.ArrayList;
import java.util.HashMap;

public class DijkstraMap {
	private HashMap<CittaNodo, CittaNodo> mappaDijkstra;

	/**
	 * Costruttore che prende come parametro una mappa e ne ritorna la versiona
	 * ottimizzata grazie all'algoritmo di Dijkstra
	 * 
	 * @param mezzoUtilizzato
	 * @param mappa
	 */
	public DijkstraMap(Veicolo mezzoUtilizzato, Mappa mappa) {
		HashMap<CittaNodo, CittaNodo> mappaDijkstra = new HashMap<>();
		CittaNodo campoBase = CittaNodo.trovaCittaInMappaId(mappa, 0);
		campoBase.setCittaCollegata(campoBase);
		campoBase.setDistanzaDalCampoBase(0);
		long distanzaMinima = Costanti.INFINITO;
		CittaNodo prossimaCitta = new CittaNodo();
		ArrayList<CittaNodo> listaCittaVisitate = new ArrayList<CittaNodo>();
		for (int i = 0; i < campoBase.getIdCittaCollegate().size(); i++) {
			CittaNodo ct2 = CittaNodo.trovaCittaInMappaId(mappa, campoBase.getIdCittaCollegate().get(i));
			double distanza = mezzoUtilizzato.calcolaDistanza(campoBase, ct2);

			if (distanza < distanzaMinima) {
				distanzaMinima = (long) distanza;
				prossimaCitta = ct2;
				
			}

			if (distanza < ct2.getDistanzaDalCampoBase()) {
				ct2.setDistanzaDalCampoBase((long) distanza);
				ct2.setCittaCollegata(campoBase);
			}
				
		}
		listaCittaVisitate.add(campoBase);
		mappaDijkstra.put(campoBase, campoBase);
		mappaDijkstra.put(prossimaCitta, campoBase);
		int numeroCittaInMappa = mappa.getMappa().keySet().size();
		while (listaCittaVisitate.size() < numeroCittaInMappa) {
			distanzaMinima = Costanti.INFINITO;
			CittaNodo cittaCorrente = prossimaCitta;
			for (int i = 0; i < cittaCorrente.getIdCittaCollegate().size(); i++) {
				CittaNodo ct2 = CittaNodo.trovaCittaInMappaId(mappa, cittaCorrente.getIdCittaCollegate().get(i));
				if (listaCittaVisitate.contains(ct2)) // Se la città e' gia' stata visitata allora la ignora
					continue;
				double distanza = mezzoUtilizzato.calcolaDistanza(cittaCorrente, ct2);

				if (distanza < distanzaMinima) {
					distanzaMinima = (long) distanza;
					prossimaCitta = ct2;

				}

				if (distanza < ct2.getDistanzaDalCampoBase()) {

					ct2.setCittaCollegata(cittaCorrente);
					ct2.setDistanzaDalCampoBase(Percorso.distanzaPrecedente(ct2, 0) + (long) distanza);

				}
			}
			mappaDijkstra.put(prossimaCitta, cittaCorrente);
			listaCittaVisitate.add(cittaCorrente);

		}
		this.mappaDijkstra = mappaDijkstra;
	}

	public HashMap<CittaNodo, CittaNodo> getMappaDijkstra() {
		return mappaDijkstra;
	}

	public void setMappaDijkstra(HashMap<CittaNodo, CittaNodo> mappaDijkstra) {
		this.mappaDijkstra = mappaDijkstra;
	}

}
