package Admin;


public enum Category {
	ROOM("Room"), TERMINAL("Terminal"), MOVIE("Movie"), FILMSHOW("Filmshow"), FORMAT("Format");

	private String label;

	private Category(String pLabel) {
		this.label = pLabel;
	}

	public String getLabel() {
		return label;
	}
	
	public static Category getCategorie(String label){
		for(Category categorie : values()) {
			if(categorie.getLabel().equals(label)){
				return categorie;
			}
		}
		return null;
	}
}
