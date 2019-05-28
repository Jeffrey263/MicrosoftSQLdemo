package p32019;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MicrosoftSQLServerBaseDao {
	protected static Connection conn;


	//Voer bij USER en PASSWORD je eigen informatie in.
	private static final String connectionUrl = "jdbc:sqlserver://localhost:1433;user=USER;password=PASSWORD;databaseName=ovDB";

  



	
	protected Connection getConnection() throws SQLException{
		if(conn == null) {
			 conn = DriverManager.getConnection(connectionUrl);
		}
		return conn;
	}
	
	public void closeConnection() throws SQLException  {
		if (conn != null) {
			conn.close();
		}
	}
}
