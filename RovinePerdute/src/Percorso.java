import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class Percorso {
	CittaNodo cittaIniziale;
	CittaNodo cittaFinale;
	double carburanteSpeso;
	ArrayList<CittaNodo> cittaToccate = new ArrayList<>();
	Veicolo mezzoUtilizzato;
	Mappa mappa;

	// costruttore che prende solo un veicolo e che imposta il resto a null
	public Percorso(Mappa mappa, Veicolo mezzoUtilizzato) {
		this.mappa = mappa;
		this.cittaFinale = CittaNodo.trovaCittaInMappaId(mappa, mappa.getMappa().size());
		this.cittaIniziale = CittaNodo.trovaCittaInMappaId(mappa, 0);
		this.mezzoUtilizzato = mezzoUtilizzato;
		this.cittaToccate = trovaCitta();
		this.carburanteSpeso = calcolaDistanza();

	}

	public Mappa convertiInDijkstra() {
		Mappa dijkstra = this.mappa;
		CittaNodo campoBase = CittaNodo.trovaCittaInMappaId(dijkstra, 0);
		long distanzaMinima = Costanti.INFINITO;
		CittaNodo prossimaCitta = new CittaNodo();
		ArrayList<CittaNodo> listaCittaVisitate = new ArrayList<CittaNodo>();
		for (int i = 0; i < campoBase.getIdCittaCollegate().size(); i++) {
			CittaNodo ct2 = CittaNodo.trovaCittaInMappaId(dijkstra, campoBase.getIdCittaCollegate().get(i));
			double distanza = mezzoUtilizzato.calcolaDistanza(campoBase, ct2);

			if (distanza < distanzaMinima) {
				distanzaMinima = (long) distanza;
				prossimaCitta = ct2;
				ct2.setCittaCollegata(campoBase);
			}

			if (distanza < ct2.getDistanzaDalCampoBase())
				ct2.setDistanzaDalCampoBase((long) distanza);
		}
		listaCittaVisitate.add(campoBase);
		int numeroCittaInMappa = dijkstra.getMappa().keySet().size();
		while (listaCittaVisitate.size() < numeroCittaInMappa) {
			distanzaMinima = Costanti.INFINITO;
			CittaNodo cittaCorrente = prossimaCitta;
			for (int i = 0; i < cittaCorrente.getIdCittaCollegate().size(); i++) {
				CittaNodo ct2 = CittaNodo.trovaCittaInMappaId(dijkstra, cittaCorrente.getIdCittaCollegate().get(i));
				if (listaCittaVisitate.contains(ct2)) // Se la città e' gia' stata visitata allora la ignora
					continue;
				double distanza = mezzoUtilizzato.calcolaDistanza(cittaCorrente, ct2);

				if (distanza < distanzaMinima) {
					distanzaMinima = (long) distanza;
					prossimaCitta = ct2;
					ct2.setCittaCollegata(cittaCorrente);
				}

				if (distanza < ct2.getDistanzaDalCampoBase())
					ct2.setDistanzaDalCampoBase(DistanzaPrecedente(ct2) + (long) distanza);
			}
			listaCittaVisitate.add(cittaCorrente);

		}

		return dijkstra;
	}

	/**
	 * Metodo ricorsivo per trovare la distanza totale precedente (dal campo base)
	 * 
	 * @param citta
	 * @return la distanza totale precedente
	 */
	public static double DistanzaPrecedente(CittaNodo citta) {
		double distanza = citta.getCittaCollegata().getDistanzaDalCampoBase();
		distanza += Percorso.DistanzaPrecedente(citta.getCittaCollegata());
		return distanza;
	}

	// metodo che data la CittaNodo campoBase e la CittaNodo a cui
	// si vuole arrivare ti ritorna una lista che contiene le citta toccate
	private ArrayList<CittaNodo> trovaCitta() {
		ArrayList<CittaNodo> cittaToccate = new ArrayList<CittaNodo>();

		return cittaToccate;
	}

	/**
	 * metodo per calcolo della distanza totale in base al tipo di mezzo
	 * 
	 * @return la distanza che sara' poi utilizzata per settare l'attributo
	 *         carburanteSpeso
	 *
	 */
	public double calcolaDistanza() {
		double distanza = mezzoUtilizzato.calcolaDistanza(cittaIniziale, cittaToccate.get(0));
		for (int i = 1; i < cittaToccate.size() - 1; i++)
			distanza += mezzoUtilizzato.calcolaDistanza(cittaToccate.get(i), cittaToccate.get(i + 1));
		return distanza;
	}

	// metodi setters e getters

	/*
	 * campo base: 1 2 3 citta 2: 4 6 3
	 */

}
