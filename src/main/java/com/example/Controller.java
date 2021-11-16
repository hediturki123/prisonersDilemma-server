package com.example;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import object.Decision;
import object.Game;
import object.Player;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class Controller {
	
	/*private Game game = new Game();
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> readGame(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(game);
	}
	
	@GetMapping("/player/{id}")
	public ResponseEntity<Player> readPlayer(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(game.findPlayerById(id));			
	}
	
	@GetMapping("/allPlayers")
	public ResponseEntity<List<Player>> readAllPlayers() {
		return ResponseEntity.ok(game.allPlayers());
	}
	*/
	@PostMapping("/home")
	public ResponseEntity<Game> createGame() {
		Player player1 = new Player();
		return ResponseEntity.ok(player1.createGame(10));
	}
	
	@PostMapping("/home")
	public void joinGame(int id) {
		Player player2 = new Player();
		player2.joinGame(findGameById(id));
	}
	
	@PostMapping("/home/game?id={idGame}/player/{idPlayer}")
	public void actionCooperate(@PathVariable(name = "idGame") int idGame, @PathVariable(name = "idGame") int idPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		player.action(game, Decision.COOPERATE);
	}
	
	@PostMapping("/home/game?id={idGame}/player/{idPlayer}")
	public void actionBetray(@PathVariable(name = "idGame") int idGame, @PathVariable(name = "idGame") int idPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		player.action(game, Decision.BETRAY);
	}
	
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
}
