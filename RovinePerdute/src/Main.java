import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class Main {

	public static void main(String[] args) throws XMLStreamException {
		Mappa mappa = new Mappa();
		
		Metztli metztli = new Metztli();
		Tonathiuh tonathiuh = new Tonathiuh();
		DijkstraMap mappaDijkstra = new DijkstraMap(metztli, mappa);
		DijkstraMap mappaDijkstraT = new DijkstraMap(tonathiuh, mappa);
		Percorso percorso = new Percorso(mappaDijkstra, metztli);
		Percorso percorsoT = new Percorso(mappaDijkstraT, tonathiuh);
		ArrayList<Percorso> list = new ArrayList<Percorso>();
		list.add(percorsoT);
		list.add(percorso);
		System.out.println(percorso.getMappaDijkstra().getMappaDijkstra());
		System.out.println(percorso.carburante());
		ScritturaFinale.generaXMLpercorsi(list);
	}

}
