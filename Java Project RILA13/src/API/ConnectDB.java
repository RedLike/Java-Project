package API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Class permettant d'établir une connexion à la base de donnée ainsi que d'y faire des requètes SQL
 */

public class ConnectDB {

	private String url;
	private String login;
	private String passwd;
	private String databaseName;
	private Connection cn;
	private Statement st;
	
	/**
	 * Constructeur par défaut
	 */
	public ConnectDB() {
		super();
		this.databaseName = "cesi_java";
		this.url = "jdbc:mysql://localhost/" + this.databaseName;
		this.login = "rila13";
		this.passwd = "LLF4t27b";
		this.cn = null;
		this.st = null;
		
		try {
			//Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//Récupération de la connexion
			this.cn = DriverManager.getConnection(this.url, this.login, this.passwd);
			//Création d'un statement
			this.st = this.cn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Cette méthode permet d'éxecuter un requète SQL de type "SELECT"
	 * @param sql est la requète sql à effectuer
	 * @return ResultSet est le résultat retourné par la requète
	 */
	@SuppressWarnings("finally")
	public ResultSet ReadDB(String sql) {
		ResultSet rs = null;
		try {
			//exécution de la requète
			rs = this.st.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Retourne résultat
			return rs;
		}
		
	}
	
	/**
	 * Cette méthode permet d'éxecuter un requète SQL de type "INSERT, UPDATE, DELETE"
	 * @param sql est la requète sql à effectuer
	 * @return Integer est le résultat retourné par la requète
	 */
	@SuppressWarnings("finally")
	public Integer WriteDB(String sql) {
		Integer rs = null;
		try {
			//exécution de la requète
			rs = this.st.executeUpdate(sql);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Retourne résultat
			return rs;
		}
		
	}
	
	
	/**
	 * Cette méthode permet de fermer la connexion actuel.
	 */
	public void CloseDB() {
		try {
			//Libère ressources de la mémoire
			this.cn.close();
			this.st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//GETTERS & SETTERS
	
	@SuppressWarnings("unused")
	private String getUrl() {
		return url;
	}


	@SuppressWarnings("unused")
	private void setUrl(String url) {
		this.url = url;
	}


	@SuppressWarnings("unused")
	private String getLogin() {
		return login;
	}


	@SuppressWarnings("unused")
	private void setLogin(String login) {
		this.login = login;
	}


	@SuppressWarnings("unused")
	private String getPasswd() {
		return passwd;
	}


	@SuppressWarnings("unused")
	private void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	@SuppressWarnings("unused")
	private String getDatabaseName() {
		return databaseName;
	}


	@SuppressWarnings("unused")
	private void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}


	@SuppressWarnings("unused")
	private Connection getCn() {
		return cn;
	}


	@SuppressWarnings("unused")
	private void setCn(Connection cn) {
		this.cn = cn;
	}


	@SuppressWarnings("unused")
	private Statement getSt() {
		return st;
	}


	@SuppressWarnings("unused")
	private void setSt(Statement st) {
		this.st = st;
	}
	
	
	
}
