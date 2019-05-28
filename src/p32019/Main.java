package p32019;

import java.awt.print.Printable;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	//private static ReizigerDAOImpl rdao;
	//private static OVchipkaartDAOImpl odao;
	//private static ProductDAOImpl pdao;
	private static ReizigerSQLServerDAOImpl rdao;
	private static OVchipkaartSQLServerDAOImpl odao;

	public static void main(String[] args) {

		try {
			//rdao = new ReizigerDAOImpl();
			rdao = new ReizigerSQLServerDAOImpl();
			odao = new OVchipkaartSQLServerDAOImpl();
			//odao = new OVchipkaartDAOImpl();
			//pdao = new ProductDAOImpl();
		} catch (SQLException sq) {
			System.out.println(sq.getMessage());
		}

		Reiziger r1 = new Reiziger("Linden", java.sql.Date.valueOf("1995-12-25"), 6, "van der", "S");
		Reiziger r2 = new Reiziger("Linden", java.sql.Date.valueOf("1995-12-25"), 6, "van der", "S");
		Reiziger r3 = new Reiziger("Language", java.sql.Date.valueOf("1994-12-02"), 7, "", "R");
		Reiziger r4 = new Reiziger("Taal", java.sql.Date.valueOf("1994-12-02"), 8, "", "R");

		// toevoegen nieuwe reizigers
		System.out.println("\nToevoegen reizigers");
		
		// System.out.println(rdao.save(r1) != null ? " Reiziger toegevoegd " + : "
		// Reiziger niet toegevoegd");
		printCreateResult(rdao.save(r1));
		printCreateResult(rdao.save(r2));
		printCreateResult(rdao.save(r3));
		printCreateResult(rdao.save(r4));
		
		// print alle reizigers
		System.out.println("Eerste lijst vanuit database ");
		printAlleReizigers(rdao);


		// print alle reizigers na toevoegen nieuwe reizigers
		System.out.println("Tweede lijst vanuit database na toevoegen nieuwe reizigers ");
		printAlleReizigers(rdao);

		// verwijderen van reiziger
		System.out.println("\nVerwijderen van reizigers");
		System.out.println(rdao.delete(r3) ? "Reiziger verwijderd " : "Reiziger niet verwijderd");

		// print alle reizigers na verwijderen reizigers
		System.out.println("Derde lijst na verwijderen reizigers ");
		printAlleReizigers(rdao);

		// zoeken reiziger op geboortedatum
		Date nieuw = java.sql.Date.valueOf("1995-12-25");
		printGevondenReizigers(rdao.findByGBdatum(nieuw));

		// updaten van reiziger
		r3.setNaam("Taal");
		r3.setGbdatum(nieuw);
		printCreateResult(rdao.update(r3));
		printGevondenReizigers(rdao.findByGBdatum(nieuw));

		// updaten van reiziger
		Date nieuw2 = java.sql.Date.valueOf("1994-12-02");
		r3.setGbdatum(nieuw2);
		printCreateResult(rdao.update(r3));
		printGevondenReizigers(rdao.findByGBdatum(nieuw));

		// printen van lijst na update
		System.out.println("Vierde lijst na updaten reizigers ");
		//printAlleReizigers(rdao);
		printAlleReizigers(rdao);
		
		//printen lijst alle kaarten
		System.out.println("\nLijst van ale kaarten");
		printAlleKaarten(odao);
		
		//toevoegen OVkaart
		Date geldigTot = java.sql.Date.valueOf("2029-04-24");
		OVchipkaart kaart1 = new OVchipkaart(3333, geldigTot, 1, 20, r1);
		OVchipkaart kaart = new OVchipkaart(3333, geldigTot, 1, 20, r1);
		OVchipkaart kaart4 = new OVchipkaart(3332, geldigTot, 2, 20, r1);
		OVchipkaart kaart2 = new OVchipkaart(3334, geldigTot, 2, 0, r4);
		
		System.out.println("\nToevoegen kaarten");
		printCreateResult(odao.save(kaart1));
		printCreateResult(odao.save(kaart));
		printCreateResult(odao.save(kaart2));
		printCreateResult(odao.save(kaart4));
		
		
		//vinden OV chipkaart op nummer, nummer 2 bestaat niet in database
		int nummer = 3332;
		int nummer2 = 46391;
		
		System.out.println("\nKaarten zoeken op nummer");
		printGevondenKaarten(odao.findByKaartnummer(nummer));
		printGevondenKaarten(odao.findByKaartnummer(nummer2));
		
		
		//vinden OV chipkaart op reiziger
		System.out.println("\nKaarten zoeken op reiziger");
		printGevondenKaarten(odao.findByReiziger(r1));
		printGevondenKaarten(odao.findByReiziger(r4));
		
		//wijzigen ov chipkaart 
		kaart1.setSaldo(100);
		kaart2.setSaldo(50);
		System.out.println("\nWijzigen kaarten");
		System.out.println("Saldo voor wijzigen " + kaart1.getSaldo() + " van kaart " + kaart1.getKaartNummer() );
		printCreateResult(odao.update(kaart1));
		System.out.println("Saldo na wijzigen " + kaart1.getSaldo() + " van kaart " + kaart1.getKaartNummer() );
		System.out.println("Saldo voor wijzigen " + kaart2.getSaldo() + " van kaart " + kaart2.getKaartNummer() );
		printCreateResult(odao.update(kaart2));
		System.out.println("Saldo na wijzigen " + kaart2.getSaldo() + " van kaart " + kaart2.getKaartNummer() );
		
		// verwijderen van kaart
		System.out.println("\nVerwijderen van kaarten");
		System.out.println(odao.delete(kaart2) ? "Kaart verwijderd " : "Kaart niet verwijderd");
		printGevondenKaarten(odao.findByKaartnummer(kaart2.getKaartNummer()));
		
		//printen lijst alle kaarten
		System.out.println("\nLijst van alle kaarten na crud");
		printAlleKaarten(odao);
		
	}

	private static void printCreateResult(OVchipkaart k) {
		if (k == null) {
			System.out.println("Kaart is niet toegevoegd/geupdated\n");
		} else {
			System.out.println(k + " is toegevoegd/geupdated\n");
		}
	}
	
	private static void printGevondenKaarten(List<OVchipkaart> alleKaarten) {
		if(alleKaarten.isEmpty()) {
			System.out.println("\nGeen kaarten gevonden met dat kaartnummer");
		}
		else {
			System.out.println("\nDe kaarten zijn gevonden met dit kaartnummer \n");
			for(OVchipkaart k : alleKaarten) {
				System.out.println(k);
			}
		}
	}

	private static void printGevondenReizigers(List<Reiziger> alleReizigers) {
		if (alleReizigers.isEmpty()) {
			System.out.println("\nGeen reiziger gevonden met die geboortedatum");
		} else {
			for (Reiziger r : alleReizigers) {
				System.out.println("\nDe reiziger met de gbDatum is gevonden \n" + r);
			}
		}
	}

	private static void printCreateResult(Reiziger r) {
		if (r == null) {
			System.out.println("Reiziger is niet toegevoegd/geupdated\n");
		} else {
			System.out.println("Reiziger " + r + " is toegevoegd/geupdated\n");
		}
	}
	
	private static void printAlleReizigers(ReizigerSQLServerDAOImpl rdao) {
		ArrayList<Reiziger> alleReizigers = rdao.findAll();

		System.out.println("\nAlle reizigers: ");
		for (Reiziger r : alleReizigers) {
			System.out.println(r);
		}
	}
	
	private static void printAlleKaarten(OVchipkaartSQLServerDAOImpl odao) {
		List<OVchipkaart> alleKaarten = odao.findAll();

		System.out.println("\nAlle kaarten: ");
		for (OVchipkaart kaart : alleKaarten) {
			System.out.println(kaart);
		}
	}
	

}
