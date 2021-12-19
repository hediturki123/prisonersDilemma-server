package com.example;


import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import game.Game;
import game.Player;

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
		try {
			game.getPlayer1().sseEmitter.send(game);
		} catch (IOException e) {
			game.getPlayer1().sseEmitter.completeWithError(e);
		}
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
		player.setHavePlayed(newPlayer.isHavePlayed());
		player.action(newPlayer.getCurrentDecision(), newPlayer.getStrategyCode());
		
		if(game.getPlayer1().getId() == player.getId()) {			
			game.setPlayer1(player);
		} else {
			game.setPlayer2(player);
		}
		if (game.getPlayer1().getCurrentDecision() != null && game.getPlayer2() != null && game.getPlayer2().getCurrentDecision() != null) {
			game.launch();
		}
		try {
			game.getPlayer2().sseEmitter.send(game);
			game.getPlayer1().sseEmitter.send(game);
		} catch (IOException e) {
			if(player.getId() == game.getPlayer1().getId()) {
				game.getPlayer2().sseEmitter.completeWithError(e);
			}
			if(player.getId() == game.getPlayer2().getId()) {
				game.getPlayer1().sseEmitter.completeWithError(e);
			}
		}
		return ResponseEntity.ok(player);			
	}

	@GetMapping("/game/waitOtherPlayer/idGame={idGame}/idPlayer={idPlayer}")
	public SseEmitter waitOtherPlayer(@PathVariable(name = "idGame")int idGame,
			@PathVariable(name = "idPlayer")int idPlayer) {
		Game game = findGameById(idGame);
		SseEmitter emitter = new SseEmitter((long)86400000);
		if (game.getPlayer1().getId() == idPlayer) {	
			if(game.getPlayer1().sseEmitter == null) {
				game.getPlayer1().sseEmitter = emitter;
			}
		} else {
			if(game.getPlayer2().sseEmitter == null) {
				game.getPlayer2().sseEmitter = emitter;	
			}
		}
		
		emitter.onCompletion(() ->
			System.out.println("")
		);
		emitter.onTimeout(emitter::complete);
		emitter.onError(ex -> emitter.complete());
		
		return emitter;
	}
	
}
