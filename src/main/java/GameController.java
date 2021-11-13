import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import object.Game;

@RestController
@CrossOrigin
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	Game game;
	
	/**
	 * 
	 */
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public void read(@RequestBody Game g) {
		game.displayScore();
	}
	
	/**
	 * 
	 */
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Game create() {
		game.play();
		return game;
	}
	
	/**
	 * 
	 * @param g
	 */
	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Game g) {
		game.calculateScore(0);
		game.calculateScore(1);
	}
	
	
}
