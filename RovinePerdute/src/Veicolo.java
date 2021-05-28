abstract public class Veicolo {

	private String nomeVeicolo;

	public Veicolo (String nome){
		this.nomeVeicolo = nome;
	}

	/**
	 * metodo specifico di ogni veicolo che indica la distanza tra due città
	 * @return distanza tra due citta' inerente al particolare consumo
	 */
	public abstract double calcolaDistanza(CittaNodo ct1, CittaNodo ct2);

	
	// Getters e setters
	public String getNomeVeicolo() {
		return nomeVeicolo;
	}

	public void setNomeVeicolo(String nomeVeicolo) {
		this.nomeVeicolo = nomeVeicolo;
	}
	
}
