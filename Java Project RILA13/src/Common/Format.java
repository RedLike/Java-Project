package Common;

public class Format {
	
	private int id;
	private String label;
	private String language;
	private String description;
	
	public Format(String label, String language, String description) {
		super();
		this.label = label;
		this.language = language;
		this.description = description;
	}
	
	//CRUD
	public void createFormat(String label, String language, String description) {
		
	}
	
	public void readFormat(){
		
	}
	
	public void updateFormat(String label, String language, String description){
		
	}
	
	public void deleteFormat(){
		
	}

	//GETTERS & SETTERS
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
