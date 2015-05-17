package Common;

public class Movie {
	
	private int id;
	private String name;
	private String duration;
	private String description;
	private String genre;
	private String author;
	private String producer;
	private String releaseDate;
	private Format format;
	
	public Movie(String name, String duration, String description, String genre, String author, String producer, String releaseDate, Format format) {
		super();
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.genre = genre;
		this.author = author;
		this.producer = producer;
		this.releaseDate = releaseDate;
		this.format = format;
	}

	//CRUD
	public void createFormat(String name, String duration, String description, String genre, String author, String producer, String releaseDate, Format format) {
		
	}
	
	public void readFormat(){
		
	}
	
	public void updateFormat(String name, String duration, String description, String genre, String author, String producer, String releaseDate, Format format){
		
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
}
