package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import object.Game;
import object.Player;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class Controller {

	Game game = new Game();
	Player player1 = new Player();
	Player player2 = new Player();
	
	
	@GetMapping("/")
    String db() {
      return "bruh";
    }
	
}
