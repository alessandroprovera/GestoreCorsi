package it.polito.tdp.corsi.model;

public class Divisione {
	
	private String CDS;
	private int nStudenti;
	
	public Divisione(String cDS, int nStudenti) {
		CDS = cDS;
		this.nStudenti = nStudenti;
	}

	public String getCDS() {
		return CDS;
	}

	public int getnStudenti() {
		return nStudenti;
	}

	@Override
	public String toString() {
		return "Divisione [CDS=" + CDS + ", nStudenti=" + nStudenti + "]";
	}
	
	

}
