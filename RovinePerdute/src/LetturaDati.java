

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Classe statica contenente metodi per l'interazione con l'utente e per la lettura del file xml
 */

public class LetturaDati {

    /**
     * metodo che stampa a video alcune indicazioni riguardo i file xml disponibili
     */
    public static void vediFileDisponibili(){
        System.out.println("Di seguito verrano elencate le mappe disponibili.");
        System.out.println("--> 1 : 5 citta'");
        System.out.println("--> 2 : 12 citta'");
        System.out.println("--> 3 : 50 citta'");
        System.out.println("--> 4 : 200 citta'");
        System.out.println("--> 5 : 2000 citta'");
        System.out.println("--> 6 : 10 000 citta'");

    }

    /**
     * metodo che restituisce l'intero corrispondende alla scelta fatta dall'utente
     * @return int che indica il file da leggere
     */
    public static int sceltaFile(){
        vediFileDisponibili();
        return InputDati.leggiIntero("Seleziona la mappa che desideri usare", 1, 6);
    }


    /**
     * metodo che richiede in input la scelta di uno degli disponibili, dal quale attingere i dati relativi alla creeazione
     * della mappa per le RovinePerdute
     *
     * @return stringa che indica il relative path dell'xml che si vuole leggere, fra quelli a disposizione
     * @see "leggiCitta"
     */
    public static String selezionaPercorsoFile(){
        String percorsoFile = " ";
        switch (sceltaFile()){
            case 1: percorsoFile = "mappeXML/PgAr_Map_5.xml"; break;
            case 2: percorsoFile = "mappeXML/PgAr_Map_12.xml"; break;
            case 3: percorsoFile = "mappeXML/PgAr_Map_50.xml"; break;
            case 4: percorsoFile = "mappeXML/PgAr_Map_200.xml"; break;
            case 5: percorsoFile = "mappeXML/PgAr_Map_2000.xml"; break;
            case 6: percorsoFile = "mappeXML/PgAr_Map_10000.xml"; break;
        }
        return percorsoFile;
    }

    /**
     * metodo che legge i dati da uno degli xml forniti e che restituisce l'insieme di tutte le città con le varie informazioni
     * @return Arraylist delle città lette, ciascuna con gli id delle citta ad essa collegate
     * @see "selezionaFile"
     */
    public static ArrayList<CittaNodo> leggiCitta() throws XMLStreamException {
        String percorsoFile = selezionaPercorsoFile();
        ArrayList<CittaNodo> listaCitta = new ArrayList<>();

        System.out.println("CREAZIONE MAPPA IN CORSO...");

        XMLInputFactory xmlif = null;
        XMLStreamReader reader = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            reader = xmlif.createXMLStreamReader(percorsoFile, new FileInputStream(percorsoFile));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        CittaNodo nuovaCitta = new CittaNodo();
        while (reader.hasNext()) { // continua a leggere finché ha eventi a disposizione


            switch (reader.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    //se l'elemento iniziale è città allora creo una nuova città settando i vari attributi e la aggiungo
                    //all'Hashmap
                    if(reader.getLocalName().equals("city")) {
                        //creo e aggiungo direttamente alla mappa una CittaNodo, con i dati presi dall'xml
                        int h = Integer.parseInt(reader.getAttributeValue(4));
                        int y = Integer.parseInt(reader.getAttributeValue(3));
                        int x = Integer.parseInt(reader.getAttributeValue(2));
                        int id = Integer.parseInt(reader.getAttributeValue(0));
                        nuovaCitta = new CittaNodo(h, y, x, reader.getAttributeValue(1), id);
                        listaCitta.add(nuovaCitta);
                    }
                    else if(reader.getLocalName().equals("link")) {
                       for(int i = 0; i < reader.getAttributeCount(); i ++){
                           //riempio l'array in CittaNodo contenente gli id di tutte le citta ad essa collegate
                           listaCitta.get(nuovaCitta.getId()).getIdCittaCollegate().add(Integer.parseInt(reader.getAttributeValue(i)));
                       }
                    }

                default: break;
            }
            reader.next();
        }
        System.out.println("MAPPA CREATA!!");
        return listaCitta;

    }
}

