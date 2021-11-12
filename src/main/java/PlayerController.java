import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import object.Player;

@RestController
@CrossOrigin
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	Player player;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Player> read(@PathVariable(value = "id") int id) {
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @param p
	 * @return
	 */
	@PostMapping("/{id}")
	public ResponseEntity<Player> create(@PathVariable(value = "id") int id, @RequestBody Player p) {
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @param p
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Player> update(@PathVariable(value = "id") int id, @RequestBody Player p) {
		return null;
	}
	
}
