package object;

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
public class Game {
	
	private int turn;
	
	private int currentTurn;
	
	public void calculateScore(int currentTurn) {
		
	}
	
	public void displayScore() {
		
	}
	
	public void play() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = player1.createGame(0);
		player2.joinGame(game);
	}

	
}
