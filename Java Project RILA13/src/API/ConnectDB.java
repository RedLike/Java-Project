package API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectDB {

	private String url;
	private String login;
	private String passwd;
	private String databaseName;
	private Connection cn;
	private Statement st;
	
	
	public ConnectDB() {
		super();
		this.databaseName = "cesi_rila13";
		this.url = "jdbc:mysql://vps104447.ovh.net/" + this.databaseName;
		this.login = "rila13";
		this.passwd = "LLF4t27b";
		this.cn = null;
		this.st = null;
		
		try {
			//Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//R�cup�ration de la connexion
			this.cn = DriverManager.getConnection(this.url, this.login, this.passwd);
			//Cr�ation d'un statement
			this.st = this.cn.createStatement();
			// Check
			System.out.println("Connexion OK?");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void ReadDB(String sql) {
		try {
			//ex�cution de la requ�te
			ResultSet rs = this.st.executeQuery(sql);
			//parcours r�sultat
			while (rs.next()) {
				System.out.println(rs.getString(0));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void CloseDB() {
		try {
			//Lib�re ressources de la m�moire
			this.cn.close();
			this.st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
