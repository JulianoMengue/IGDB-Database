package com.julianomengue.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julianomengue.entity.Game;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class GameService {

	private String clientId = "9elezo1801iqpfolq8iaison0q35lv";
	private String Bearer = "Bearer osdu66akp344dme3mroa1vxm05dhig";
	private String json = "application/json";

	private String games = "https://api.igdb.com/v4/games";

	public List<Game> findByName(String name) throws UnirestException, IOException {
		HttpResponse<JsonNode> jsonResponse = Unirest.post(games).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields platforms.name,cover.url,name; limit 500; search \"+" + name
						+ "+\"; where cover.url != null ;")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Game> games = objectMapper.readValue(jsonResponse.getBody().toString().replaceAll("t_thumb", "t_1080p"),
				new TypeReference<List<Game>>() {
				});
		return games;
	}

	public List<Game> findByYear(String year) throws UnirestException, IOException, ParseException {
		long begin = getYearBegin(year);
		long end = getYearEnd(year);

		HttpResponse<JsonNode> jsonResponse = Unirest.post(games).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields platforms.name,cover.url,name; limit 500; sort release_dates.date desc; where release_dates.date >"
						+ begin + "  & release_dates.date <" + end + " & cover.url != null;")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Game> games = objectMapper.readValue(jsonResponse.getBody().toString().replaceAll("t_thumb", "t_1080p"),
				new TypeReference<List<Game>>() {
				});
		return games;
	}

	public Game findById(String id) throws UnirestException, IOException {
		HttpResponse<JsonNode> response = Unirest.post(games).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields cover.url," + "genres.name," + "name," + "platforms.name," + "release_dates.human,"
						+ "release_dates.platform.name," + "screenshots.url," + "release_dates.region," + "summary,"
						+ "url," + "websites.url," + "videos.video_id;" + " where id =" + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Game> games = objectMapper.readValue(response.getBody().toString().replaceAll("t_thumb", "t_1080p"),
				new TypeReference<List<Game>>() {
				});
		return games.get(0);
	}

	public long getYearBegin(String ano) throws ParseException {
		String yearBegin = "01/01/" + ano;
		Date yearBeginDate = new SimpleDateFormat("dd/MM/yyyy").parse(yearBegin);
		Timestamp timestampYearBegin = new Timestamp(yearBeginDate.getTime());
		long epoch = timestampYearBegin.getTime() / 1000;
		return epoch;
	}

	public long getYearEnd(String ano) throws ParseException {
		String yearEnd = "31/12/" + ano;
		Date yearEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(yearEnd);
		Timestamp timestampYearEndDate = new Timestamp(yearEndDate.getTime());
		long epochYearEndDate = timestampYearEndDate.getTime() / 1000;
		return epochYearEndDate;
	}

	public Game getRegion(Game game) {
		for (int i = 0; i < game.getRelease_dates().size(); i++) {
			if (game.getRelease_dates().get(i).getRegion().contentEquals("8")) {
				game.getRelease_dates().get(i).setRegion("Worldwide");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("7")) {
				game.getRelease_dates().get(i).setRegion("Asia");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("6")) {
				game.getRelease_dates().get(i).setRegion("China");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("5")) {
				game.getRelease_dates().get(i).setRegion("Japan");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("4")) {
				game.getRelease_dates().get(i).setRegion("New Zealand");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("3")) {
				game.getRelease_dates().get(i).setRegion("Australia");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("2")) {
				game.getRelease_dates().get(i).setRegion("North America");
			}
			if (game.getRelease_dates().get(i).getRegion().contentEquals("1")) {
				game.getRelease_dates().get(i).setRegion("Europe");
			}
		}
		return game;
	}

}
