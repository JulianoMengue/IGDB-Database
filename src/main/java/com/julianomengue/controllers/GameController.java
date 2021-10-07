package com.julianomengue.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julianomengue.entity.Cover;
import com.julianomengue.entity.Game;
import com.julianomengue.services.GameService;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@RequestMapping()
public class GameController {

	@Autowired
	private GameService gameService;

	private String noImage = "https://st4.depositphotos.com/14953852/22772/v/600/depositphotos_227725020-stock-illustration-image-available-icon-flat-vector.jpg";

	String error = "Not allowed!";

	@GetMapping()
	public String games(Model model) throws UnirestException, IOException {
		Game game = new Game();
		this.gameService.psStore();
		List<Game> games = this.gameService.findAll();
		model.addAttribute("game", game);
		model.addAttribute("cont", games.size());
		model.addAttribute("games", games);
		return "games/search-game";
	}

	@GetMapping("/search")
	public String search(Model model, Game game) throws UnirestException, IOException {
		List<Game> games = this.gameService.findAll(game.getName());
		model.addAttribute("cont", games.size());
		model.addAttribute("games", this.gameService.withoutCover(games));
		return "games/games";
	}

	@GetMapping("/showGame")
	public String showGame(Model model, @RequestParam String id) throws UnirestException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		Game game = this.gameService.findById(id);
		if (game.getCover() == null) {
			game.setCover(new Cover(noImage));
		}
		model.addAttribute("game", game);
		return "games/game";
	}

}
