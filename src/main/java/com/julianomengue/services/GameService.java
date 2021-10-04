package com.julianomengue.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julianomengue.entity.Cover;
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
	private String noImage = "https://st4.depositphotos.com/14953852/22772/v/600/depositphotos_227725020-stock-illustration-image-available-icon-flat-vector.jpg";

	private String games = "https://api.igdb.com/v4/games";

	public List<Game> findAll(String name) throws UnirestException, IOException {
		HttpResponse<JsonNode> jsonResponse = Unirest.post(games).header("Client-ID", clientId)
				.header("Authorization", Bearer).header("Accept", json)
				.body("fields platforms.name,cover.url,name; limit 300; search \"+" + name + "+\";").asJson();
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
						+ "release_dates.platform.name," + "screenshots.url," + "summary," + "url," + "websites.url,"
						+ "videos.video_id;" + " where id =" + id + ";")
				.asJson();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Game> games = objectMapper.readValue(response.getBody().toString().replaceAll("t_thumb", "t_1080p"),
				new TypeReference<List<Game>>() {
				});
		return games.get(0);
	}

	public List<Game> withoutCover(List<Game> games) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getCover() == null) {
				games.get(i).setCover(new Cover(noImage));
			}
		}
		return games;
	}

}
