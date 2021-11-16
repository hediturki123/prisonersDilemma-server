package object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Round {
	
	private Decision movePlayer1;
	
	private Decision movePlayer2;
	
	public void playRound(Game game) {
		while(game.getPlayer1().getCurrentDecision() == null &&
				game.getPlayer2().getCurrentDecision() == null) {}
		movePlayer1 = game.getPlayer1().getCurrentDecision();
		movePlayer2 = game.getPlayer2().getCurrentDecision();
	}
}
