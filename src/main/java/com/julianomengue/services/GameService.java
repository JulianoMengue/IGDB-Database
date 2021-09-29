package com.julianomengue.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julianomengue.entity.Cover;
import com.julianomengue.entity.Game;
import com.julianomengue.entity.Genre;
import com.julianomengue.entity.Platform;
import com.julianomengue.entity.ReleaseDate;
import com.julianomengue.entity.Screenshot;
import com.julianomengue.entity.Video;
import com.julianomengue.entity.Website;
import com.julianomengue.repositories.GameRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	private String clientId = "9elezo1801iqpfolq8iaison0q35lv";
	private String Bearer = "Bearer osdu66akp344dme3mroa1vxm05dhig";
	private String json = "application/json";

	private String games = "https://api.igdb.com/v4/games";
	private String covers = "https://api.igdb.com/v4/covers";
	private String screenshots = "https://api.igdb.com/v4/screenshots";
	private String genres = "https://api.igdb.com/v4/genres";
	private String release_dates = "https://api.igdb.com/v4/release_dates";
	private String platforms = "https://api.igdb.com/v4/platforms";
	private String videos = "https://api.igdb.com/v4/game_videos";
	private String websites = "https://api.igdb.com/v4/websites";

	public void save(Game game) {
		this.gameRepository.save(game);
	}

	public List<Game> findAll() {
		return this.gameRepository.findAll();
	}

	public Game findByIdMongoDB(String id) {
		return this.gameRepository.findById(id).get();
	}

	public List<Game> findAll(String name) throws UnirestException, IOException {
		HttpResponse<JsonNode> jsonResponse = Unirest.post(games).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields cover,name; limit 100; search \"+" + name + "+\";").asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Game> games = objectMapper.readValue(jsonResponse.getBody().toString(), new TypeReference<List<Game>>() {
		});
		return games;
	}

	public Game findById(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> response = Unirest.post(games).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields cover,genres,name,platforms,release_dates,screenshots,summary,url,videos,websites; where id ="
						+ id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Game> games = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Game>>() {
		});
		return games.get(0);
	}

	public List<Cover> getCover(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> jsonResponse = Unirest.post(covers).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json).body("fields url; where id = " + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Cover> covers = objectMapper.readValue(jsonResponse.getBody().toString().replaceAll("t_thumb", "t_720p"),
				new TypeReference<List<Cover>>() {
				});

		return covers;
	}

	public List<Screenshot> getScreenshots(String id)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(screenshots).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields game,height,image_id,url,width; where id=" + id + ";").asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Screenshot> screenshot = objectMapper.readValue(
				response.getBody().toString().replaceAll("t_thumb", "t_720p"), new TypeReference<List<Screenshot>>() {
				});
		return screenshot;
	}

	public List<Genre> getGenre(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> jsonResponse = Unirest.post(genres).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json).body("fields name; where id = " + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Genre> genres = objectMapper.readValue(jsonResponse.getBody().toString(),
				new TypeReference<List<Genre>>() {
				});
		return genres;
	}

	public List<ReleaseDate> getDate(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> jsonResponse = Unirest.post(release_dates).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields human,platform; where id = " + id + ";").asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<ReleaseDate> releaseDates = objectMapper.readValue(jsonResponse.getBody().toString(),
				new TypeReference<List<ReleaseDate>>() {
				});
		return releaseDates;
	}

	public List<Platform> getPlatform(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> response = Unirest.post(platforms).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json).body("fields name; where id = " + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Platform> platforms = objectMapper.readValue(response.getBody().toString(),
				new TypeReference<List<Platform>>() {
				});
		return platforms;
	}

	public List<Video> getVideo(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> response = Unirest.post(videos).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json).body("fields video_id; where id = " + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Video> videos = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Video>>() {
		});
		return videos;
	}

	public List<Website> getWebsite(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> response = Unirest.post(websites).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json).body("fields url; where id = " + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Website> websites = objectMapper.readValue(response.getBody().toString(),
				new TypeReference<List<Website>>() {
				});
		return websites;
	}

}
