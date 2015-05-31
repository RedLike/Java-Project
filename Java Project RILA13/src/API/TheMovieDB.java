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
import java.util.ArrayList;
import java.util.List;


/**
 * Class permettant d'utiliser l'API "The Movie DB" afin de récuperer les informations sur des films depuis le site Th Movie DB.
 */

public class TheMovieDB {

	private String apiUrl;
	private String apiKey;
	private JSONObject json;


	/**
	 * Constructeur par défaut
	 * @throws JSONException 
	 */
	public TheMovieDB() throws JSONException {
		this.apiUrl = "https://api.themoviedb.org/3/";
		this.apiKey = "1d15fc6e7ee80f72efda46176ed01d8a";

	}

	
	/**
	 * Cette méthode permet de télécharger un fichier JSON depuis une URL
	 * @param query Qui est la requête pour TheMovieDB API
	 * @return booleen "true" si le JSON a été récupéré, dans le cas inverse retourne "false"
	 * @throws JSONException 
	 */
	private boolean downloadJson(String query) throws JSONException {
		String urlString = apiUrl + query;
		urlString += (query.indexOf("?") > -1) ? "&" : "?";
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
	

	/**
	 * Méthode appelée pour récuperé la liste des derniers films à l'affiche
	 * @param listFormat afin de récupérer uniquement les films dont la langue est supportée
	 * @return List<movie> Retourne une list de tous les films récent
	 * @throws JSONException 
	 */
	public List<Movie> Discover(ArrayList<Format> listFormat) throws JSONException {
		List<Movie> moviesDiscover = null;
		if(downloadJson("discover/movie?")) {
			moviesDiscover = new ArrayList<>();
			
			JSONArray jsonArray = getJson().getJSONArray("results");
			for(int i = 0; i < jsonArray.length(); i++) {

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
	
	
	
	/**
	 * Méthode de récuperer des informations plus précise sur un film
	 * @param movieFromSearch est le film necessitant de faire une recherche complémentaire
	 * @param listFormat afin de vérifier si le film est disponible dans le format demandé (langue)
	 * @return Movie Retourne l'objet Movie complété
	 */
	public Movie getInfos(Movie movieFromSearch, ArrayList<Format> listFormat) {
		Movie movie = null;

		try {
			if(downloadJson("movie/" + movieFromSearch.getIdMovieDB())) {

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
					
					for(Format x : listFormat) {
						if (x.getLabel().equals(format.toString()) ) {
							movieFromSearch.setFormat(x);
						}
					}
				}
//				System.out.println(movieFromSearch.getName());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movie;
	}
	
	
	/**
	 * Méthode permettant d'inserer les films en base de données
	 * @param listMovie est la liste des films à ajouter
	 */
	public void Insert(List<Movie> listMovie) {
		for(Movie movie : listMovie) {
			movie.create();
		}
	}
	
	
	
	
	//GETTERS & SETTERS
	@SuppressWarnings("unused")
	private String getApiKey() {
		return apiKey;
	}

	@SuppressWarnings("unused")
	private void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@SuppressWarnings("unused")
	private String getApiUrl() {
		return apiUrl;
	}

	@SuppressWarnings("unused")
	private void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	private JSONObject getJson() {
		return json;
	}

	@SuppressWarnings("unused")
	private void setJson(JSONObject json) {
		this.json = json;
	}

}