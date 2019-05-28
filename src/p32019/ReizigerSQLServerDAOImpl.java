package p32019;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReizigerSQLServerDAOImpl extends MicrosoftSQLServerBaseDao implements ReizigerDAO {

	public ReizigerSQLServerDAOImpl() throws SQLException {
		super.getConnection();
	}
	
	@Override
	public ArrayList<Reiziger> findAll() {
		ArrayList<Reiziger> alleReizigers = new ArrayList<Reiziger>();
		Reiziger reiziger = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from reiziger");
			while (rs.next()) {
				reiziger = fillIntoObject(rs);
				alleReizigers.add(reiziger);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleReizigers;
	}
	
	public Reiziger findByID(int ID) {
		Reiziger r = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM reiziger WHERE reizigerid = '" + ID+ "'"); 
			while(rs.next()) {
				r = fillIntoObject(rs);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return r;
	}

	public List<Reiziger> findByGBdatum(Date GBdatum) {
		ArrayList<Reiziger> reizigersGB = new ArrayList<Reiziger>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM reiziger WHERE gebortedatum = '" + GBdatum + "'");
			while(rs.next()) {
				Reiziger r = fillIntoObject(rs);
				reizigersGB.add(r);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return reizigersGB;
	}

	public Reiziger save(Reiziger reiziger) {
		try {
			System.out.println("De volgende reiziger gaat toegevoegd worden " + reiziger);
			Connection con = super.getConnection();
			String q = "INSERT INTO reiziger(reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1, reiziger.getID());
			ps.setString(2, reiziger.getVoorletters());
			ps.setString(3, reiziger.getTussenvoegel());
			ps.setString(4, reiziger.getNaam());
			ps.setDate(5, (java.sql.Date) reiziger.getGBdatum());
			int r = ps.executeUpdate();
			if(r > 0) {
				System.out.println("Reiziger toegevoegd");
			}
		} catch (Exception e) {
			reiziger = null;
			//e.printStackTrace();
		}
		return reiziger;
	}

	public Reiziger update(Reiziger r) {
		System.out.println("De volgende reizer wordt gewijzigd " + r );
		try {
			Statement stmt = conn.createStatement();
			int st = stmt.executeUpdate("UPDATE reiziger\n" + "SET voorletters = '" + r.getVoorletters()
			+ "',\n" + "tussenvoegsel = '" + r.getTussenvoegel() + "',\n" + "achternaam = '"
			+ r.getNaam() + "',\n" + "gebortedatum = '" +  r.getGBdatum() + "' WHERE reizigerid = " + r.getID());
			if(st > 0) {
				System.out.println("Reiziger gewijzigd");
			}
			stmt.close();
		}catch(Exception e){
			r = null;
			System.out.println(e.getMessage());
		}
		return r;
	}


	public boolean delete(Reiziger r) {
		System.out.println("De volgende reizer word verwijdert " + r );
		boolean verwijderd = false;
		try {
			Statement stmt = conn.createStatement();
			int st = stmt.executeUpdate("DELETE FROM reiziger\n" + "WHERE reizigerid = " + r.getID());
			if(st > 0) {
				verwijderd = true;
			}
			stmt.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			verwijderd = false;
		}
		return verwijderd;
	}
	
	public Reiziger fillIntoObject(ResultSet rs) throws SQLException {
		return new Reiziger(rs.getString("achternaam"), rs.getDate("gebortedatum"), rs.getInt("reizigerid"),
				rs.getString("tussenvoegsel"), rs.getString("voorletters"));
	}


}
