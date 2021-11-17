package com.example;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import object.Decision;
import object.Game;
import object.Player;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Controller {
	
	@GetMapping("/home")
	public ResponseEntity<Game> createGame() {
		Player player1 = new Player();
		return ResponseEntity.ok(player1.createGame(10));
	}
	
	@GetMapping("/home/{idGame}")
	public ResponseEntity<Game> joinGame(@PathVariable(name = "idGame") int id) {
		Player player2 = new Player();
		Game game = findGameById(id);
		player2.joinGame(game);
		return ResponseEntity.ok(game);
	}
	
	@GetMapping("/home/game/{id}")
	public ResponseEntity<Game> readGame(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(findGameById(id));
	}
	
	/*@PostMapping("/home/game?id={idGame}/player/{idPlayer}")
	public void actionCooperate(@PathVariable(name = "idGame") int idGame, @PathVariable(name = "idGame") int idPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		player.action(Decision.COOPERATE);
	}
	
	@PostMapping("/home/game?id={idGame}/player/{idPlayer}")
	public void actionBetray(@PathVariable(name = "idGame") int idGame, @PathVariable(name = "idGame") int idPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		player.action(Decision.BETRAY);
	}*/
	
	public Game findGameById(int id) {
		int i = 0;
		while (i < RestServer.games.size() && RestServer.games.get(i).getId() != id) {
			i++;
		}
		if (i == RestServer.games.size()) {
			return null;
		}
		return RestServer.games.get(i);
	}
	
	@GetMapping("/home/game/{idGame}/player/{idPlayer}")
	public ResponseEntity<Player> readPlayer(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.findPlayerById(idPlayer));			
	}
	
	@GetMapping("home/game/{idGame}/allPlayers")
	public ResponseEntity<List<Player>> readAllPlayers(@PathVariable(name = "idGame") int idGame) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.allPlayers());
	}
}
