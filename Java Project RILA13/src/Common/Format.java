package Common;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Interface.ADM;

public class Format implements ADM{
	
	private Integer id;
	private String label;
	private String language;
	private String description;
	
	/**
	 * Empty Format constructor 
	 */
	public Format() {
		super();
	}
	
	/**
	 * Format constructor with parameters
	 * @param label
	 * @param language
	 * @param description
	 */
	public Format(String label, String language, String description) {
		this();
		this.label = label;
		this.language = language;
		this.description = description;
	}
	
	/**
	 * Format constructor with parameters and its db id
	 * @param id
	 * @param label
	 * @param language
	 * @param description
	 */
	public Format(int id, String label, String language, String description) {
		this(label, language, description);
		this.id = id;
	}
	
	//CRUD
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#create()
	 */
	public boolean create() {
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Label, Language, Description FROM Format "
							+ "WHERE Label='"+this.label+"' AND Language='"+this.language+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Format(Label, Language, Description) VALUES('"+this.label+"', '"+this.language+"', '"+this.description+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;
				
			} else {
				setId(result0.getInt("Id"));
				System.out.println("Format already exist");
				res = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
	
	/**
	 * Return a boolean that is false if the Format don't exist in the database or if the request doesn't succeed
	 * @return res
	 */
	public boolean read(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Label, Language, Description FROM Format "
							+ "WHERE Id='"+this.id+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				System.out.println("Format don't exist");
			}
			else
			{
				System.out.println(result0.getString(0));
				res = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
	
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#update()
	 */
	public boolean update(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Format "
					+ "SET Label='"+this.label+"' AND Language='"+this.language+"' AND Description='"+this.description+"'"
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
	
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#delete()
	 */
	public boolean delete(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Format WHERE Id='"+this.id+"'";
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

	//GETTERS & SETTERS
	
	/**
	 * Get Integer value of the id attribute
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set Integer value of the id attribute
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get String value of the label attribute
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Set String value of the label attribute
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get String value of the language attribute
	 * @return language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Set String value of the language attribute
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Get String value of the description attribute
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set String value of the description attribute
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
