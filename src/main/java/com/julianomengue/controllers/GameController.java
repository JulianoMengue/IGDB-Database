package com.julianomengue.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julianomengue.entity.Cover;
import com.julianomengue.entity.Game;
import com.julianomengue.entity.Genre;
import com.julianomengue.entity.Platform;
import com.julianomengue.entity.ReleaseDate;
import com.julianomengue.entity.Screenshot;
import com.julianomengue.entity.Video;
import com.julianomengue.entity.Website;
import com.julianomengue.services.GameService;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@RequestMapping()
public class GameController {

	@Autowired
	private GameService gameService;

	String error = "Not allowed!";

	@GetMapping()
	public String games(Model model) {
		Game game = new Game();
		List<Game> games = this.gameService.findAll();
		Collections.reverse(games);
		model.addAttribute("games", games);
		model.addAttribute("game", game);
		model.addAttribute("cont", games.size());
		return "games/search-game";
	}

	@PostMapping("/search")
	public String search(Model model, Game game) throws UnirestException, IOException {
		List<Game> games = this.gameService.findAll(game.getName());
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getCover() != null) {
				String coverId = games.get(i).getCover();
				List<Cover> covers = this.gameService.getCover(coverId);
				games.get(i).setCover(covers.get(0).getUrl());
			} else {
				games.get(i).setCover(
						"https://st4.depositphotos.com/14953852/22772/v/600/depositphotos_227725020-stock-illustration-image-available-icon-flat-vector.jpg");
			}

		}

		model.addAttribute("cont", games.size());
		model.addAttribute("games", games);
		return "games/games";
	}

	@GetMapping("/showGame")
	public String showGame(Model model, @RequestParam String id) throws UnirestException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		Game game = this.gameService.findById(id);

		List<Cover> covers = this.gameService.getCover(game.getCover());

		List<String> screenshotsIds = game.getScreenshots();
		List<String> genresIds = game.getGenres();
		List<String> datesIds = game.getRelease_dates();
		List<String> videosIds = game.getVideos();
		List<String> websitesIds = game.getWebsites();

		List<Video> videos = new ArrayList<>();
		List<Screenshot> screenshots = new ArrayList<>();
		List<Genre> genres = new ArrayList<>();
		List<ReleaseDate> releaseDates = new ArrayList<>();
		List<Platform> platforms = new ArrayList<>();
		List<Website> websites = new ArrayList<>();

		for (int i = 0; i < websitesIds.size(); i++) {
			websites.add(this.gameService.getWebsite(websitesIds.get(i)).get(0));
			game.getWebsites().set(i, websites.get(i).getUrl());
		}

		for (int i = 0; i < videosIds.size(); i++) {
			videos.add(this.gameService.getVideo(videosIds.get(i)).get(0));
			videos.get(i).setVideo_id("https://www.youtube.com/embed/" + videos.get(i).getVideo_id());
			game.getVideos().set(i, videos.get(i).getVideo_id());
		}

		for (int i = 0; i < genresIds.size(); i++) {
			genres.add(this.gameService.getGenre(genresIds.get(i)).get(0));
			game.getGenres().set(i, genres.get(i).getName());
		}

		for (int i = 0; i < screenshotsIds.size(); i++) {
			screenshots.add(this.gameService.getScreenshots(screenshotsIds.get(i)).get(0));
			game.getScreenshots().set(i, screenshots.get(i).getUrl());
		}

		for (int i = 0; i < datesIds.size(); i++) {
			releaseDates.add(this.gameService.getDate(datesIds.get(i)).get(0));
		}

		for (int i = 0; i < releaseDates.size(); i++) {
			platforms.add(this.gameService.getPlatform(releaseDates.get(i).getPlatform()).get(0));
		}

		for (int y = 0; y < covers.size(); y++) {
			String url = covers.get(y).getUrl();
			game.setCover(url);
		}

		for (int i = 0; i < platforms.size(); i++) {
			releaseDates.get(i).setHuman(releaseDates.get(i).getHuman() + " " + platforms.get(i).getName());
			game.getRelease_dates().set(i, releaseDates.get(i).getHuman());
		}
		this.gameService.save(game);
		model.addAttribute("game", game);
		return "games/game";
	}

	@GetMapping("/showGameMongoDB")
	public String showGameMongoDB(Model model, @RequestParam String id) {
		model.addAttribute("game", this.gameService.findByIdMongoDB(id));
		return "games/game";
	}

}
