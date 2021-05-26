public class Metztli extends Veicolo{

    public Metztli() {

    }

    /**
     * Calcola la distanza del veicolo Metztli
     * @param ct1 prima città
     * @param ct2 seconda città
     * @return la distanza percorsa ottenuta dalla formula
     */
    public int calcolaDistanza(CittaNodo ct1, CittaNodo ct2) {

        int h1 = ct1.getPosizione().getH();
        int h2 = ct2.getPosizione().getH();

        return Math.abs(h2-h1);
    }
}
