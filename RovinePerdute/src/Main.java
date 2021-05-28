import javax.xml.stream.XMLStreamException;

public class Main {

	public static void main(String[] args) throws XMLStreamException {
		Mappa mappa = new Mappa();
		Metztli metztli = new Metztli();
		DijkstraMap mappaDijkstra = new DijkstraMap(metztli, mappa);
		Percorso percorso = new Percorso(mappaDijkstra, metztli);
		System.out.println(percorso.getMappaDijkstra().getMappaDijkstra());
		System.out.println(percorso.carburante());
	}

}
