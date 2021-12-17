package object;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Round {
	
	private Decision movePlayer1;
	
	private Decision movePlayer2;
	
	public void playRound(Game game) {
		movePlayer1 = game.getPlayer1().getCurrentDecision();
		movePlayer2 = game.getPlayer2().getCurrentDecision();
		game.setCurrentRound(game.getCurrentRound()+1);
		game.getPlayer1().setCurrentDecision(null);
		game.getPlayer1().setHavePlayed(false);
		game.getPlayer2().setCurrentDecision(null);
		game.getPlayer2().setHavePlayed(false);
	} 
}
