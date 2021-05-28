import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class Percorso {
	private CittaNodo cittaIniziale;
	private CittaNodo cittaFinale;
	private double carburanteSpeso;
	private ArrayList<CittaNodo> cittaToccate = new ArrayList<>();
	private Veicolo mezzoUtilizzato;
	private Mappa mappa;

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
	public ArrayList<CittaNodo> trovaCitta() {
		ArrayList<CittaNodo> cittaToccate = new ArrayList<CittaNodo>();
		cittaToccate.add(cittaFinale.getCittaCollegata());
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



    //GETTERS E SETTERS

	
	public CittaNodo getCittaFinale() {
		return cittaFinale;
	}

	public CittaNodo getCittaIniziale() {
		return cittaIniziale;
	}

	public void setCittaIniziale(CittaNodo cittaIniziale) {
		this.cittaIniziale = cittaIniziale;
	}

	public Mappa getMappa() {
		return mappa;
	}

	public void setMappa(Mappa mappa) {
		this.mappa = mappa;
	}

	public void setCittaFinale(CittaNodo cittaFinale) {
		this.cittaFinale = cittaFinale;
	}

	public double getCarburanteSpeso() {
		return carburanteSpeso;
	}

	public void setCarburanteSpeso(double carburanteSpeso) {
		this.carburanteSpeso = carburanteSpeso;
	}

	public ArrayList<CittaNodo> getCittaToccate() {
		return cittaToccate;
	}

	public void setCittaToccate(ArrayList<CittaNodo> cittaToccate) {
		this.cittaToccate = cittaToccate;
	}

	public Veicolo getMezzoUtilizzato() {
		return mezzoUtilizzato;
	}

	public void setMezzoUtilizzato(Veicolo mezzoUtilizzato) {
		this.mezzoUtilizzato = mezzoUtilizzato;
	}
	

}
