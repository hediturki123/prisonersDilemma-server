package object;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Game {
	
	private int turn;
	
	private int currentTurn;
	
	public void calculateScore(int currentTurn) {
		
	}
	
	public void displayScore() {
		
	}
	
	public void play() {
		
	}

	
}
