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

import object.Decision;
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
	
	@GetMapping("/game/{id}")
	public ResponseEntity<Game> readGame(@PathVariable(name = "id") int id) {
		Game game = findGameById(id);
		return ResponseEntity.ok(game);
	}
	
	@GetMapping("game/lastGame")
	public ResponseEntity<Game> readLastGame() {
		return ResponseEntity.ok(RestServer.games.get(RestServer.games.size() - 1));
	}
	/*
	@PutMapping("/game/{id}")
	public ResponseEntity<Game> updateGame(@PathVariable(name = "id") int id, 
			@RequestBody Game g) {
		if (g.getId() == id) {
			return ResponseEntity.ok(g);			
		} else {
			 return ResponseEntity.status(401).body(null);
		}
	}*/
	
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
	
	@GetMapping("/game/{idGame}/player/{idPlayer}")
	public ResponseEntity<Player> readPlayer(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.findPlayerById(idPlayer));			
	}
	
	/*@PutMapping("/game/{idGame}/player/{idPlayer}")
	public ResponseEntity<Player> updatePlayer(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		player.setScore(3);
		return ResponseEntity.ok(player);			
	}*/
	
	@PutMapping("/game/{idGame}/player/{idPlayer}")
	public ResponseEntity<Player> updatePlayerDecision(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer, @RequestBody String newPlayer) {
		Game game = findGameById(idGame);
		Player player = game.findPlayerById(idPlayer);
		//player.setId(newPlayer.getId());
		player.setScore(3);
		player.setCurrentDecision(Decision.COOPERATE);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa : " + newPlayer);
		return ResponseEntity.ok(player);			
	}
	
	/*@GetMapping("/play/{idGame}/{idPlayer}")
	public ResponseEntity<Decision> readDecision(@PathVariable(name = "idGame") int idGame, 
			@PathVariable(name = "idPlayer") int idPlayer) {
		Game game = findGameById(idGame);
		return ResponseEntity.ok(game.getPlayer1().getCurrentDecision());
	}*/
	
	
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
