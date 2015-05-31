package API;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Common.Format;
import Common.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TheMovieDB {

	private String apiUrl;
	private String apiKey;
	private JSONObject json;

	private boolean initialized;

	/**
	 * Constructeur par défaut
	 * @throws JSONException 
	 */
	public TheMovieDB() throws JSONException {
		this.apiUrl = "https://api.themoviedb.org/3/";
		this.apiKey = "1d15fc6e7ee80f72efda46176ed01d8a";

		initialized = true;
	}

	
	/**
	 * Cette méthode permet de télécharger un fichier JSON depuis une URL
	 * @param query Qui est la requête pour TheMovieDB API
	 * @return booleen "true" si le JSON a été récupéré, "false" sinon
	 * @throws JSONException 
	 */
	private boolean downloadJson(String query) throws JSONException {
		if(!initialized) return false;

		String urlString = apiUrl + query;
		urlString += (query.indexOf("?") > -1) ? "&" : "?";
//		urlString += "api_key=" + apiKey + "&language=" + apiLang;
		urlString += "api_key=" + apiKey;

		try{
			URL url = new URL(urlString);

			String json = "";
			String line = "";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = bufferedReader.readLine()) != null){
				json += line;
			}
			bufferedReader.close();

			this.json = new JSONObject(json);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	

	public List<Movie> Discover(ArrayList<Format> listFormat) throws JSONException {
		List<Movie> moviesDiscover = null;
		if(downloadJson("discover/movie?")) {
			moviesDiscover = new ArrayList<>();
			
			JSONArray jsonArray = getJson().getJSONArray("results");
			for(int i = 0; i < jsonArray.length(); i++) {
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				int idMovieDB = 0;
				String original_title = null;
				String release_date = null;
				String poster_path = null;
				String overview = null;
				
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				if(!jsonObject.isNull("id")){
					idMovieDB = jsonObject.getInt("id");
				}
					
				if(!jsonObject.isNull("original_title")) {
					original_title = jsonObject.getString("original_title");
					original_title = original_title.replace("'", "''");
				}
					
				if(!jsonObject.isNull("release_date")) {
					release_date = jsonObject.getString("release_date");
				}
					
				if(!jsonObject.isNull("poster_path")) {
					poster_path = jsonObject.getString("poster_path");
				}
					
				if(!jsonObject.isNull("overview")) {
					overview = jsonObject.getString("overview");
					overview = overview.replace("'", "GRRDR");
				}	
					
				Movie movie = new Movie(original_title, idMovieDB, poster_path, overview, release_date);


				getInfos(movie, listFormat);
				
				moviesDiscover.add(movie);
			}
			
			
		}
		
		return moviesDiscover;
	}
	
//	public List<Movie> search(String title) {
//		List<Movie> movies = null;
//
//		if(downloadJson("search/movie?query=" + title)) {
//			movies = new ArrayList<>();
//
//			JSONArray jsonArray = getJson().getJSONArray("results");
//			for(int i = 0; i < jsonArray.length(); i++) {
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//				try {
//					Movie movie = new Movie();
//					if(!jsonObject.isNull("id"))
//						movie.setId(jsonObject.getInt("id"));
//					if(!jsonObject.isNull("original_title"))
//						movie.setLabel(jsonObject.getString("original_title"));
//					if(!jsonObject.isNull("release_date"))
//						movie.setDate(simpleDateFormat.parse(jsonObject.getString("release_date")));
//
//					getInfos(movie);
//					movies.add(movie);
//				} catch(ParseException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return movies;
//	}
	
	

	public Movie getInfos(Movie movieFromSearch, ArrayList<Format> listFormat) {
		Movie movie = null;

		try {
			if(downloadJson("movie/" + movieFromSearch.getIdMovieDB())) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				JSONObject jsonObject = getJson();
				if(!jsonObject.isNull("runtime"))
					movieFromSearch.setDuration(jsonObject.getInt("runtime"));
					
				if(!jsonObject.isNull("production_companies")) {
					String compagnie;
					
					compagnie = jsonObject.getJSONArray("production_companies").get(0).toString();
					compagnie = compagnie.substring(9,compagnie.indexOf("\",\"",9));
					
					compagnie = compagnie.replace("'", "''");
					movieFromSearch.setProducer(compagnie);
					
				}
					
				if(!jsonObject.isNull("genres")) {
					String genre;
					
					genre = jsonObject.getJSONArray("genres").get(0).toString();
					genre = genre.substring(9,genre.indexOf("\",\"",9));
					
					movieFromSearch.setGenre(genre);
				}
					
				if(!jsonObject.isNull("spoken_languages")) {
					String format;
					
					format = jsonObject.getJSONArray("spoken_languages").get(0).toString();
					format = format.substring(9,format.indexOf("\",\"",9));
//					System.out.println("testo");
//					System.out.println(listFormat.size());
					
					for(Format x : listFormat) {
						if (x.getLabel().equals(format.toString()) ) {
							movieFromSearch.setFormat(x);
						}
					}
				}
					
				
				System.out.println(movieFromSearch.getName());
				
//				System.out.println(jsonObject.getInt("runtime")+" == "+movieFromSearch.getDuration());
//				
//				JSONArray compagniep = jsonObject.getJSONArray("production_companies");
//				Object compa = compagniep.get(0);
//				String compagni = compa.toString();
//				compagni = compagni.substring(9,compagni.indexOf("\",\"",7));
//				System.out.println(compagni+" == "+movieFromSearch.getProducer());
//				
//				JSONArray genrep = jsonObject.getJSONArray("genres");
//				Object gen = genrep.get(0);
//				String ge = gen.toString();
//				ge = ge.substring(9,ge.indexOf("\",\"",9));
//				System.out.println(ge+" == "+movieFromSearch.getGenre());
//				
//				JSONArray formatp = jsonObject.getJSONArray("spoken_languages");
//				Object forma = formatp.get(0);
//				String form = forma.toString();
//				form = form.substring(9,form.indexOf("\",\"",9));
//				String readFormat = "SELECT id, Label, Language, Description FROM format";
//				ConnectDB db = new ConnectDB();
//				ResultSet resultat = db.ReadDB(readFormat);
//				try {
//					while (resultat.next()) {
////						System.out.println(resultat.getString("Label"));
////						System.out.println(form);
//						if (resultat.getString("Label").toString().equals(form.toString()) ) {
//							System.out.println(form+" == "+movieFromSearch.getFormat().getLabel());
//						}
//
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				db.CloseDB();
////				System.out.println(form+" == "+movieFromSearch.getFormat().getLabel());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movie;
	}
	
	
	
	public void Insert(List<Movie> listMovie) {
		for(Movie movie : listMovie) {
			movie.create();
		}
	}
	
	
	
	
	//GETTERS & SETTERS
	private String getApiKey() {
		return apiKey;
	}

	private void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	private String getApiUrl() {
		return apiUrl;
	}

	private void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	private JSONObject getJson() {
		return json;
	}

	private void setJson(JSONObject json) {
		this.json = json;
	}

	private boolean isInitialized() {
		return initialized;
	}

	private void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

}