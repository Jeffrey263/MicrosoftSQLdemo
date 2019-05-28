package p32019;

import java.util.Date;

public class Reiziger {
	//private static int nummer = 0; 
	private int idnummer;
	private String naam;
	private String tussenvoegel;
	private String voorletters;
	private Date gbdatum;
	
	public Reiziger(String nm, Date datu, int nummer, String tussn, String v) {
		idnummer = nummer;
		naam = nm;
		gbdatum = datu;
		tussenvoegel = tussn;
		voorletters = v;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String nm) {
		naam = nm;
	}
	
	public Date getGBdatum() {
		return gbdatum;
	}
	
	public void setGBdatum(Date datum) {
		gbdatum = datum;
	}
	
	public int getID() {
		return idnummer;
	}
	
	public String toString() {
		if(tussenvoegel == null) {
			tussenvoegel = "";
		}
		String s = "Naam: " + voorletters + " " + tussenvoegel + " " + naam + " GBdatum: " + gbdatum + " ID: " + idnummer;
		return s;
	}
	
	public boolean equals(Object andereObject) {
		if(andereObject instanceof Reiziger) {
			Reiziger r = (Reiziger)andereObject;
			if(r.getGBdatum().equals(gbdatum) && r.getNaam().equals(naam) && r.getID() == idnummer
					&& r.getTussenvoegel().equals(tussenvoegel) && r.getVoorletters().equals(voorletters)){
				return true;
			}
		}
		return false;
	}

	public int getIdnummer() {
		return idnummer;
	}

	public String getTussenvoegel() {
		return tussenvoegel;
	}

	public String getVoorletters() {
		return voorletters;
	}

	public Date getGbdatum() {
		return gbdatum;
	}

	public void setIdnummer(int idnummer) {
		this.idnummer = idnummer;
	}

	public void setTussenvoegel(String tussenvoegel) {
		this.tussenvoegel = tussenvoegel;
	}

	public void setVoorletters(String voorletters) {
		this.voorletters = voorletters;
	}

	public void setGbdatum(Date gbdatum) {
		this.gbdatum = gbdatum;
	}
}
