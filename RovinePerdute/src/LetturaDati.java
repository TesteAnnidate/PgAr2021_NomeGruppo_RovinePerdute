

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
        System.out.println(Costanti.PRESENTAZIONE_FILES);
        System.out.println(Costanti.FILE_5_CITTA);
        System.out.println(Costanti.FILE_12_CITTA);
        System.out.println(Costanti. FILE_50_CITTA);
        System.out.println(Costanti.FILE_200_CITTA);
        System.out.println(Costanti.FILE_2000_CITTA);
        System.out.println(Costanti.FILE_10000_CITTA);

    }

    /**
     * metodo che restituisce l'intero corrispondende alla scelta fatta dall'utente
     * @return int che indica il file da leggere
     */
    public static int sceltaFile(){
        vediFileDisponibili();
        return InputDati.leggiIntero(Costanti.SELEZIONA_FILE, 1, 6);
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
            case 1: percorsoFile = Costanti.PERCORSO_FILE_5; break;
            case 2: percorsoFile = Costanti.PERCORSO_FILE_12; break;
            case 3: percorsoFile = Costanti.PERCORSO_FILE_50; break;
            case 4: percorsoFile = Costanti.PERCORSO_FILE_200; break;
            case 5: percorsoFile = Costanti.PERCORSO_FILE_2000; break;
            case 6: percorsoFile = Costanti.PERCORSO_FILE_10000; break;
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

        System.out.println(Costanti.LETTURA_IN_CORSO);

        XMLInputFactory xmlif = null;
        XMLStreamReader reader = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            reader = xmlif.createXMLStreamReader(percorsoFile, new FileInputStream(percorsoFile));
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_INIZIALIZZAZIONE_READER);
            System.out.println(e.getMessage());
        }

        CittaNodo nuovaCitta = new CittaNodo();
        while (reader.hasNext()) { // continua a leggere finché ha eventi a disposizione


            switch (reader.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    //se l'elemento iniziale è città allora creo una nuova città settando i vari attributi e la aggiungo
                    //all'Hashmap
                    if(reader.getLocalName().equals(Costanti.LOCAL_NAME_CITY)) {
                        //creo e aggiungo direttamente alla mappa una CittaNodo, con i dati presi dall'xml
                        int h = Integer.parseInt(reader.getAttributeValue(4));
                        int y = Integer.parseInt(reader.getAttributeValue(3));
                        int x = Integer.parseInt(reader.getAttributeValue(2));
                        int id = Integer.parseInt(reader.getAttributeValue(0));
                        nuovaCitta = new CittaNodo(h, y, x, reader.getAttributeValue(1), id);
                        listaCitta.add(nuovaCitta);
                    }
                    else if(reader.getLocalName().equals(Costanti.LOCAL_NAME_LINK)) {
                       for(int i = 0; i < reader.getAttributeCount(); i ++){
                           //riempio l'array in CittaNodo contenente gli id di tutte le citta ad essa collegate
                           listaCitta.get(nuovaCitta.getId()).getIdCittaCollegate().add(Integer.parseInt(reader.getAttributeValue(i)));
                       }
                    }

                default: break;
            }
            reader.next();
        }
        System.out.println(Costanti.MAPPA_CREATA);
        return listaCitta;

    }
}

