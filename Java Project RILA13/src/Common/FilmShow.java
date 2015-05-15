package Common;

public class FilmShow {
	
	private String hour;
	private String date;
	private boolean visible;
	private Room room;
	private Movie movie;
	private Cinema cinema;
	
	public FilmShow(String hour, String date, boolean visible, Room room, Movie movie, Cinema cinema) {
		super();
		this.hour = hour;
		this.date = date;
		this.visible = visible;
		this.room = room;
		this.movie = movie;
		this.cinema = cinema;
		
		addFilmShow(this.hour, this.date, this.visible, this.room.getNumber(), this.movie.getName(), this.cinema.getName());
		
	}
	
	
	

}
