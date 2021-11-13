package object;

import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	
	private int id;
	
	private int score;
	
	private Strategy strategy;
	
	List<Decision> history;
	
	public Game createGame(int nbTurns) {
		return null;
	}
	
	public void joinGame(Game game) {
		
	}
	
	public Decision action() {
		return null;
	}

}
