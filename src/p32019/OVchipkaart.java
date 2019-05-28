package p32019;

import java.util.ArrayList;
import java.util.Date;

public class OVchipkaart {
	private int kaartNummer;
	private Date geldigTot;
	private int klasse;
	private double saldo;
	private Reiziger kaartHouder;
	
	public OVchipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, Reiziger kaartHouder) {
		this.kaartNummer = kaartNummer;
		this.geldigTot = geldigTot;
		this.klasse = klasse;
		this.saldo = saldo;
		this.kaartHouder = kaartHouder;
	}
	public int getKaartNummer() {
		return kaartNummer;
	}
	public Date getGeldigTot() {
		return geldigTot;
	}
	public int getKlasse() {
		return klasse;
	}
	public double getSaldo() {
		return saldo;
	}
	public Reiziger getKaartHouder() {
		return kaartHouder;
	}
	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}
	public void setGeldigTot(Date geldigTot) {
		this.geldigTot = geldigTot;
	}
	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void setKaartHouder(Reiziger kaartHouder) {
		this.kaartHouder = kaartHouder;
	}
	
	public String toString() {
		return "Kaart: "+ kaartNummer + ", " + geldigTot + ", " + klasse + ", " + saldo + ", Kaarthouder: " + kaartHouder;
	}

	
}
