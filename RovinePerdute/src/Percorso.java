import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class Percorso {
	private CittaNodo cittaIniziale;
	private CittaNodo cittaFinale;
	private double carburanteSpeso;
	private ArrayList<CittaNodo> cittaToccate = new ArrayList<>();
	private Veicolo mezzoUtilizzato;
	private DijkstraMap mappaDijkstra;

	// costruttore che prende solo un veicolo e che imposta il resto a null
	public Percorso(DijkstraMap mappa, Veicolo mezzoUtilizzato) {
		this.mappaDijkstra = mappa;
		this.cittaFinale = CittaNodo.trovaCittaInMappaDijkstraId(mappa, mappa.getMappaDijkstra().size() - 1);
		this.cittaIniziale = CittaNodo.trovaCittaInMappaDijkstraId(mappa, 0);
		this.mezzoUtilizzato = mezzoUtilizzato;
		this.cittaToccate = trovaCitta();
		this.carburanteSpeso = 0;

	}

	public double carburante() {
		return cittaFinale.getDistanzaDalCampoBase();
	}

	/**
	 * Metodo ricorsivo per trovare la distanza totale precedente (dal campo base)
	 * 
	 * @param citta
	 * @return la distanza totale precedente
	 */
	public static double distanzaPrecedente(CittaNodo citta, double distanza) {
		while (citta.getCittaCollegata().getDistanzaDalCampoBase() != 0) {
			distanza += citta.getCittaCollegata().getDistanzaDalCampoBase();
			return Percorso.distanzaPrecedente(citta.getCittaCollegata(), distanza);
		}
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
	/*
	 * public double calcolaDistanza() { double distanza =
	 * mezzoUtilizzato.calcolaDistanza(cittaIniziale, cittaToccate.get(0)); for (int
	 * i = 1; i < cittaToccate.size() - 1; i++) distanza +=
	 * mezzoUtilizzato.calcolaDistanza(cittaToccate.get(i), cittaToccate.get(i +
	 * 1)); return distanza; }
	 * 
	 */

	// GETTERS E SETTERS

	public CittaNodo getCittaFinale() {
		return cittaFinale;
	}

	public CittaNodo getCittaIniziale() {
		return cittaIniziale;
	}

	public void setCittaIniziale(CittaNodo cittaIniziale) {
		this.cittaIniziale = cittaIniziale;
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

	public DijkstraMap getMappaDijkstra() {
		return mappaDijkstra;
	}

	public void setMappaDijkstra(DijkstraMap mappaDijkstra) {
		this.mappaDijkstra = mappaDijkstra;
	}

}
