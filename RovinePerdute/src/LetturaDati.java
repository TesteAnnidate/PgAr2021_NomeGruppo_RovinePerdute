

//import it.unibs.fp.mylib.InputDati;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
    /*public static int sceltaFile(){
        vediFileDisponibili();
        return InputDati.leggiIntero("Seleziona la mappa che desideri usare", 1, 6);
    }*/


    /**
     * metodo che richiede in input la scelta di uno degli disponibili, dal quale attingere i dati relativi alla creeazione
     * della mappa per le RovinePerdute
     *
     * @return stringa che indica il relative path dell'xml che si vuole leggere, fra quelli a disposizione
     * @see "leggiCitta"
     */
    /*public static String selezionaFile(){
        String percorsoFile = " ";
        switch (sceltaFile()){
            case 1: percorsoFile = "mappeXML/PgAr_Map_5.xml";
            case 2: percorsoFile = "mappeXML/PgAr_Map_12.xml";
            case 3: percorsoFile = "mappeXML/PgAr_Map_50.xml";
            case 4: percorsoFile = "mappeXML/PgAr_Map_200.xml";
            case 5: percorsoFile = "mappeXML/PgAr_Map_2000.xml";
            case 6: percorsoFile = "mappeXML/PgAr_Map_10000.xml";
        }
        return percorsoFile;
    }*/

    /**
     * metodo che legge i dati da uno degli xml forniti e che restituisce l'insieme di tutte le città con le varie informazioni
     * @return Hashmap che come chiavi ha le singole città e come valori ha gli array di città
     * @see "selezionaFile"
     */
    public static HashMap leggiCitta() throws XMLStreamException {
        String percorsoFile = "mappeXML/PgAr_Map_5.xml";
        HashMap<CittaNodo, ArrayList<CittaNodo>> mappa = new HashMap<>();
        //ci inserisco e memorizzo tutti gli id
        HashMap<Integer, ArrayList<Integer>> idCittaCollegate = new HashMap<>();

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
                        mappa.put(nuovaCitta, new ArrayList<CittaNodo>());

                        idCittaCollegate.put(id, new ArrayList<>());
                    }
                    else if(reader.getLocalName().equals("link")) {
                        //se la città del link to esiste già nella mappa allora gliela aggiungo, altrimenti passo avanti
                        //una volta che l'avrò messa allora "torno indietro ad impostarla"
                        //controllo se la città con quell'id è già presente
                        //NON SONO BIDIREZIONALI OSTREGA!
                        CittaNodo collegata = CittaNodo.trovaCittaDaID(mappa, Integer.parseInt(reader.getAttributeValue(0)));
                        if(collegata.getId() != -1){
                            //se la citta con tale id era già presente come chiave allora la aggiungo all'array di città
                            //collegate a quella che sto considerando attutalmente
                            mappa.get(nuovaCitta).add(collegata);
                            //rimuovo l'id della collegata dalla lista di cui sto tenendo traccia
                            idCittaCollegate.get(nuovaCitta.getId()).remove(collegata.getId());
                            //controllo che la citta collegata abbia un riferimento a quella che sto considerando attualmente
                            //se è cosi creo il collegamento anche in quella
                            if(idCittaCollegate.get(collegata.getId()).contains(nuovaCitta.getId())){
                                mappa.get(collegata).add(nuovaCitta);
                                idCittaCollegate.get(collegata.getId()).remove(nuovaCitta.getId());
                            }
                        }
                    }

                default: break;
            }
            reader.next();
        }
        //controllo di aver impostato tutti i collegamenti andando a vedere se tutti gli array di idCittaCollegate sono vuoti
        //se ne becco uno che non lo è allora ripasso
        Set<Integer> keys = idCittaCollegate.keySet();
        for(Integer chiave : keys) {
            if(idCittaCollegate.get(chiave).size() != 0) {
                //se è diversa da 0 vuol dire che non ho aggiunto qualche collegamento

            }
        }

        return mappa;

    }
}

