import javax.xml.stream.XMLStreamException;

public class Main {

	public static void main(String[] args) throws XMLStreamException {
		Mappa mappa = new Mappa();
		Metztli metztli = new Metztli();
		Percorso percorso = new Percorso(mappa, metztli);
	}

}
