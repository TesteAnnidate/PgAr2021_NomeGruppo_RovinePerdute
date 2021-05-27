abstract public class Veicolo {

	private String nomeVeicolo;

	public Veicolo (String nome){
		this.nomeVeicolo = nome;
	}

	/**
	 * metodo specifico di ogni veicolo che indica la distanza tra due citt�
	 * @return distanza tra due citta' inerente al particolare consumo
	 */
	public abstract double calcolaDistanza(CittaNodo ct1, CittaNodo ct2);

	public String getNomeVeicolo() {
		return nomeVeicolo;
	}
}
