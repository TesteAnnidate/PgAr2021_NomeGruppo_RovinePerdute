import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class LetturaDati {

    /**
     * metodo che legge i dati da uno degli xml forniti e che restituisce l'insieme di tutte le città con le varie informazioni
     *
     * @param percorsoFile stringa che indica il percorso del file da leggere
     * @return Hashmap che come chiavi ha le singole città e come valori ha gli array di città
     */
    public static HashMap leggiCitta(String percorsoFile) throws XMLStreamException {
        CittaNodo codice_fiscale = null;
        HashMap<CittaNodo, ArrayList<CittaNodo>> mappa = new HashMap<>();

        XMLInputFactory xmlif = null;
        XMLStreamReader reader = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            reader = xmlif.createXMLStreamReader(percorsoFile, new FileInputStream(percorsoFile));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (reader.hasNext()) { // continua a leggere finché ha eventi a disposizione

            CittaNodo nuovaCitta = new CittaNodo();
            switch (reader.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    //se l'elemento iniziale è città allora creo una nuova città settando i vari attributi e la aggiungo
                    //all'Hashmap
                    if(reader.getLocalName().equals("city")) {
                        //creo e aggiungo direttamente alla mappa una CittaNodo, con i dati presi dall'xml
                        int h = Integer.parseInt(reader.getAttributeValue(0));
                        int y = Integer.parseInt(reader.getAttributeValue(1));
                        int x = Integer.parseInt(reader.getAttributeValue(2));
                        int id = Integer.parseInt(reader.getAttributeValue(4));
                        nuovaCitta = new CittaNodo(h, y, x, reader.getAttributeValue(3), id);
                        mappa.put(nuovaCitta, new ArrayList<CittaNodo>());
                    }
                    else if(reader.getLocalName().equals("link to")) {
                        //se la città del link to esiste già nella mappa allora gliela aggiungo, altrimenti passo avanti
                        //una volta che l'avrò messa allora "torno indietro ad impostarla"
                        for (int i = 0; i < reader.getAttributeCount(); i++){
                            //controllo se la città con quell'id è già presente
                            CittaNodo collegata = CittaNodo.trovaCittaDaID(mappa, Integer.parseInt(reader.getAttributeValue(i)));
                            if(collegata.getId() != -1){
                                mappa.put(nuovaCitta, mappa.get(nuovaCitta).add(collegata));
                            }
                        }
                    }

                default: break;
            }
            reader.next();
        }
        return mappa;

    }
}

