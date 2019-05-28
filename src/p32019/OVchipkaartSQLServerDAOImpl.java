package p32019;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OVchipkaartSQLServerDAOImpl extends MicrosoftSQLServerBaseDao implements OvchipkaartDAO{
	
	private ReizigerSQLServerDAOImpl rdao = new ReizigerSQLServerDAOImpl();
	
	public OVchipkaartSQLServerDAOImpl() throws SQLException{
		super.getConnection();
	}
	
	
	public List<OVchipkaart> findAll() {
		List<OVchipkaart> alleKaarten = new ArrayList<OVchipkaart>();
		OVchipkaart deKaart = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ov_chipkaart");
			while (rs.next()) {
				int kaarthouderID = rs.getInt("ReizigerID");
				Reiziger reiziger = rdao.findByID(kaarthouderID);
				deKaart = fillIntoObject(rs, reiziger);
				alleKaarten.add(deKaart);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return alleKaarten;
	}

	public List<OVchipkaart> findByKaartnummer(int kaartnummer) {
		List<OVchipkaart> alleKaarten = new ArrayList<OVchipkaart>();
		OVchipkaart deKaart = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ov_chipkaart WHERE kaartnummer = '" + kaartnummer + "'");
			while (rs.next()) {
				int kaarthouderID = rs.getInt("ReizigerID");
				Reiziger reiziger = rdao.findByID(kaarthouderID);
				deKaart = fillIntoObject(rs, reiziger);
				alleKaarten.add(deKaart);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return alleKaarten;
	}

	public List<OVchipkaart> findByReiziger(Reiziger r) {
		List<OVchipkaart> alleKaarten = new ArrayList<OVchipkaart>();
		OVchipkaart kaart = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ov_chipkaart WHERE reizigerID = '" + r.getID() + "'");
			while (rs.next()) {
				kaart = fillIntoObject(rs, r);
				alleKaarten.add(kaart);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return alleKaarten;
	}

	public OVchipkaart save(OVchipkaart kaart) {
		try {
			System.out.println("De volgende kaart wordt toegevoegd " + kaart);
			String q = "INSERT INTO ov_chipkaart (kaartnummer, geldigtot, klasse, saldo, reizigerid) VALUES (?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, kaart.getKaartNummer());
			stmt.setDate(2, (Date) kaart.getGeldigTot());
			stmt.setInt(3, kaart.getKlasse());
			stmt.setDouble(4, kaart.getSaldo());
			stmt.setInt(5, kaart.getKaartHouder().getID());
			stmt.executeUpdate();
			stmt.close();
		}catch(Exception e){
				System.out.println(e.getMessage());
				kaart = null;
			}
		return kaart;
	}

	
	public OVchipkaart update(OVchipkaart kaart) {
		System.out.println("De volgende kaart wordt gewijzigd " + kaart);
		try {
			String q = "Update ov_chipkaart SET kaartnummer = ?, geldigtot =?, klasse=?, saldo=?, reizigerid=? WHERE kaartnummer = ?";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, kaart.getKaartNummer());
			stmt.setDate(2, (Date) kaart.getGeldigTot());
			stmt.setInt(3, kaart.getKlasse());
			stmt.setDouble(4, kaart.getSaldo());
			stmt.setInt(5, kaart.getKaartHouder().getID());
			stmt.setInt(6, kaart.getKaartNummer());
			stmt.executeUpdate();
			stmt.close();;	
			System.out.println("Kaart gewijzigd");
		}catch(Exception e){
			kaart = null;
			System.out.println(e.getMessage());
		}
		return kaart;
	}

	
	public boolean delete(OVchipkaart kaart) {
		System.out.println("De volgende kaart word verwijdert " + kaart );
		boolean verwijderd = false;
		try {
			Statement stmt = conn.createStatement();
			int st = stmt.executeUpdate("DELETE FROM ov_chipkaart\n" + "WHERE kaartnummer = " + kaart.getKaartNummer());
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
	
	public OVchipkaart fillIntoObject(ResultSet rs, Reiziger kaarthouder) throws SQLException {
		return new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"),
				rs.getDouble("saldo"), kaarthouder);
	}


}
