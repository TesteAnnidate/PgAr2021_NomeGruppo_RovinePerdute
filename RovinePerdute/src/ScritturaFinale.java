import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ScritturaFinale {

    /**
     * metodo che inserisce in un xml i dati relativi al percorso migliore per ogni veicolo scelto
     * @param percorsiPerVeicolo lista dei percorsi relativi ad ogni veicolo scelto
     */

    public static void generaXMLpercorsi(ArrayList<Percorso> percorsiPerVeicolo){
        //ho messo un Array perchè se dovvessero esserci più di due team sarebbe più comodo per farli scorrere

        String file_name = Costanti.PERCORSO_FILE_DA_GENERARE;

        //inizializzazione variabili per la scrittura
        XMLOutputFactory xmlof = null;
        XMLStreamWriter writer = null;

        try {
            xmlof = XMLOutputFactory.newInstance();
            writer = xmlof.createXMLStreamWriter(new FileOutputStream(file_name), "utf-8");

            writer.writeStartDocument(Costanti.ENCODING, Costanti.VERSION); //chiuso
            writer.writeStartElement(Costanti.ROUTES); // chiuso


            for(Percorso percorso: percorsiPerVeicolo){
                writer.writeStartElement(Costanti.ROUTE); //chiuso
                writer.writeAttribute(Costanti.TEAM, percorso.getMezzoUtilizzato().getNomeVeicolo());
                writer.writeAttribute(Costanti.COSTO_CARBURANTE, Double.toString(percorso.getCarburanteSpeso()));
                writer.writeAttribute(Costanti.CITIES, Integer.toString(percorso.getCittaToccate().size()));

                for(CittaNodo cittaToccata: percorso.getCittaToccate()){
                    writer.writeStartElement(Costanti.CITY);
                    writer.writeAttribute(Costanti.ID, Integer.toString(cittaToccata.getId()));
                    writer.writeAttribute(Costanti.NAME, cittaToccata.getNome());
                    writer.writeEndElement();  //chiude "city"

                }

            }

            writer.writeEndElement(); //chiude "route"
            writer.writeEndElement();  //chiude "routes"
            writer.writeEndDocument(); // scrittura fine documento
            writer.flush(); // svuota il buffer e procede alla scrittura
            writer.close(); // chiusura del documento e delle risorse impiegate

        } catch (Exception e) {
            System.out.println(Costanti.MESSAGGIO_ECCEZIONE);

        }
    }

}


