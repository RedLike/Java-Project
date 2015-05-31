package Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Interface.ADM;

/**
 * Class permettant de g�rer les connexion et cr�ation des utilisateurs.
 */
public class UserAdmin implements ADM {

	private Integer id;
	private String name;
	private String login;
	private String password;
	
	
	//CONSTRUCTOR
	/**
	 * Constructeur par d�faut
	 * "private" car non instanciable sous cette forme
	 */
	private UserAdmin() {
		super();
	}
	
	/**
	 * Constructeur par d�faut pour la fonction "login"
	 */
	public UserAdmin(String login, String password) {
		this();
		this.id = null;
		this.name = null;
		this.login = login;
		this.password = password;
	}
	
	/**
	 * Constructeur par d�faut pour la cr�ation de compte utilisateur
	 */
	public UserAdmin(String name, String login, String password) {
		this(login, password);
		this.id = null;
		this.name = name;
	}
	
	/**
	 * Constructeur secondaire pour la g�n�ration des objets utilisateurs
	 */
	public UserAdmin(Integer Id, String name, String login, String password) {
		this(name, login, password);
		this.id = Id;
	}

	
	
	//GETTERS & SETTERS
	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@SuppressWarnings("unused")
	private String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	// FUNCTION
	/**
	 * Cette m�thode permet de v�rifier si les "login"/"password" fournit sont correcte
	 * @return booleen "true" si les informations corresponde avec la BDD, sinon false
	 */
	public boolean login() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Name, Login, Password FROM UserAdmin "
							+ "WHERE Login='"+this.login+"' AND Password='"+this.password+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (result0.next()) {
				
				setId(result0.getInt("Id"));
				setName(result0.getString("Name"));
				res = true;
				
			} else {
				res = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
		
	
	
	//CRUD
	/**
	 * Cette m�thode permet de cr�er un utilisateur en base de donn� ci celui-ci n'est pas d�j� pr�sent
	 * @return booleen "true" si la cr�ation est r�ussi, "false" si d�j� pr�sent
	 */
	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Name, Login, Password FROM UserAdmin "
							+ "WHERE Name='"+this.name+"' AND Login='"+this.login+"' AND Password='"+this.password+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO UserAdmin(Name, Login, Password) VALUES('"+this.name+"', '"+this.login+"', '"+this.password+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;
				
			} else {
				setId(result0.getInt("Id"));
				System.out.println("UserAdmin already exist");
				res = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}

	
	/**
	 * Cette m�thode permet de mettre � jour un profil utilisateur dans la BDD
	 * @return booleen "true" si mise � jour r�ussi, "false" sinon
	 */
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE UserAdmin "
					+ "SET Name='"+this.name+"', Login='"+this.login+"', Password='"+this.password+"'"
					+ "WHERE Id='"+this.id+"'";
			if (db.WriteDB(sqlUpdate0) != null) {
				res = true;
			} else {
				res = false;
			}
			
			
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
	

	/**
	 * Cette m�thode permet de suprimer l'utilisateur de la BDD
	 * @return booleen "true" si la suppression a �t� r�ussi, false sinon
	 */
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM UserAdmin WHERE Id='"+this.id+"'";
			if (db.WriteDB(sqlDelete0) != null) {
				res = true;
			} else {
				res = false;
			}
			
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
	
	
	
}
