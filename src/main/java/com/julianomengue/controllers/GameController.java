package com.julianomengue.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julianomengue.entity.Game;
import com.julianomengue.entity.Year;
import com.julianomengue.services.GameService;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@RequestMapping()
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping()
	public String games(Model model) throws UnirestException, IOException {
		model.addAttribute("year", new Year());
		model.addAttribute("game", new Game());
		return "games/search-game";
	}

	@GetMapping("/search")
	public String search(Model model, Game game) throws UnirestException, IOException {
		List<Game> games = this.gameService.findByName(game.getName());
		model.addAttribute("year", new Year());
		model.addAttribute("game", new Game());
		model.addAttribute("cont", games.size());
		model.addAttribute("games", games);
		return "games/search-game";
	}

	@GetMapping("/showGame")
	public String showGame(Model model, @RequestParam String id) throws UnirestException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		Game game = this.gameService.findById(id);
		model.addAttribute("year", new Year());
		model.addAttribute("game", game);
		return "games/game";
	}

	@GetMapping("/year")
	public String year(Model model, Year year) throws UnirestException, IOException, ParseException {
		List<Game> games = this.gameService.findByYear(year.getYear());
		model.addAttribute("game", new Game());
		model.addAttribute("cont", games.size());
		model.addAttribute("year", new Year());
		model.addAttribute("newYear", year.getYear());
		model.addAttribute("games", games);
		return "games/search-game";
	}

}
