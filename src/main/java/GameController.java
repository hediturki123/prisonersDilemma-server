import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<Game> read() {
		return null;
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<Game> create(@RequestBody Game g) {
		return null;
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 */
	@PutMapping("/")
	public ResponseEntity<Game> update(@RequestBody Game g) {
		return null;
	}
	
	
}
