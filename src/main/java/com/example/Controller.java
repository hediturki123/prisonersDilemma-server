package com.example;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import object.Game;
import object.Player;
import object.Round;

@RestController
@CrossOrigin
@RequestMapping("/home")
public class Controller {
	
	@PostMapping("/{nbTurns}")
	public ResponseEntity<Game> createGame(@PathVariable(name = "nbTurns") int nbTurns) {
		Player player1 = new Player();
		return ResponseEntity.ok(player1.createGame(nbTurns));
	}
	
	@PutMapping("/game/{idGame}")
	public ResponseEntity<Game> joinGame(@PathVariable(name = "idGame") int id) {
		Player player2 = new Player();
		Game game = findGameById(id);
		player2.joinGame(game);
		return ResponseEntity.ok(game);
	}
	
	@PutMapping("/game/{idGame}/{currentRound}")
	public ResponseEntity<Game> updateGame(@PathVariable(name = "idGame") int id, @PathVariable(name = "currentRound") int currentRound, @RequestBody Game newGame) {
		Game game = findGameById(id);
		game.setCurrentRound(newGame.getCurrentRound());
		game.setNbTurns(newGame.getNbTurns());
		game.setPlayer1(newGame.getPlayer1());
		game.setPlayer2(newGame.getPlayer2());
		game.setHistory(newGame.getHistory());
		return ResponseEntity.ok(game);
	}
	
	@GetMapping("/game/{id}")
	public ResponseEntity<Game> readGame(@PathVariable(name = "id") int id) {
		Game game = findGameById(id);
		return ResponseEntity.ok(game);
	}
	
	@GetMapping("game/lastGame")
	public ResponseEntity<Game> readLastGame() {
		return ResponseEntity.ok(RestServer.getGames().get(RestServer.getGames().size() - 1));
	}
	
	public Game findGameById(int id) {
		int i = 0;
		while (i < RestServer.getGames().size() && RestServer.getGames().get(i).getId() != id) {
			i++;
		}
		if (i == RestServer.getGames().size()) {
			return null;
		}
		return RestServer.getGames().get(i);
	}
	
	@GetMapping("/game/{idGame}/player/{idPlayer}")
	public ResponseEntity<Player> readPlayer(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.findPlayerById(idPlayer));			
	}
	
	@PutMapping("/game/{idGame}/player/{idPlayer}")
	public ResponseEntity<Player> updatePlayer(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer, @RequestBody Player newPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		player.setScore(newPlayer.getScore());
		player.setCurrentDecision(newPlayer.getCurrentDecision());
		player.action(newPlayer.getCurrentDecision(), 0);
		if (game.getPlayer1().getCurrentDecision() != null && game.getPlayer2() != null && game.getPlayer2().getCurrentDecision() != null) {
			game.launch();
		}
		return ResponseEntity.ok(player);			
	}
	
	@GetMapping("/game/{idGame}/allRounds")
	public ResponseEntity<List<Round>> readAllRounds(@PathVariable(name = "idGame") int idGame) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.getHistory());
	}
	
	@GetMapping("/game/{idGame}/allPlayers")
	public ResponseEntity<List<Player>> readAllPlayers(@PathVariable(name = "idGame") int idGame) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.allPlayers());
	}
}
