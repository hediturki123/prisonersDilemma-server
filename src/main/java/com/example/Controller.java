package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import object.Game;
import object.Player;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class Controller {
	
	private Game game = new Game();
	
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
	
	
}
