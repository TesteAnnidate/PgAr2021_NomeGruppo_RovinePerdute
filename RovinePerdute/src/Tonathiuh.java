public class Tonathiuh extends Veicolo {

    //costruttore vuoto se serve
    public Tonathiuh() {

    }

    /**
     * Calcola la distanza del veicolo Tonathiuh
     * @param ct1 prima città
     * @param ct2 seconda città
     * @return la distanza percorsa ottenuta dalla formula
     */
    public int  calcolaDistanza(CittaNodo ct1, CittaNodo ct2) {

        int x1 = ct1.getPosizione().getX();
        int x2 = ct2.getPosizione().getY();

        int y1 = ct1.getPosizione().getX();
        int y2 = ct1.getPosizione().getY();

        return (int) Math.sqrt(Math.pow(x2-x1,2)+ Math.pow(y2-y1,2));

    }
}
