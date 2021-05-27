public class Tonathiuh extends Veicolo {

    //costruttore vuoto se serve
    public Tonathiuh() {
        super("Tonathiu");
    }

    /**
     * Calcola la distanza del veicolo Tonathiuh tra 2 citta'
     * @param ct1 prima citta'
     * @param ct2 seconda citta' 
     * @return la distanza percorsa ottenuta dalla formula
     */
    public double calcolaDistanza(CittaNodo ct1, CittaNodo ct2) {

        double x1 = ct1.getPosizione().getX();
        double x2 = ct2.getPosizione().getY();

        double y1 = ct1.getPosizione().getX();
        double y2 = ct1.getPosizione().getY();

        return Math.sqrt(Math.pow(x2-x1,2)+ Math.pow(y2-y1,2));

    }
}
