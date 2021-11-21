package object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Round {
	
	private Decision movePlayer1;
	
	private Decision movePlayer2;
	
	public synchronized void playRound(Game game) {
			while(game.getPlayer1().getCurrentDecision() == null &&
					game.getPlayer2().getCurrentDecision() == null) {
				try {
					wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			movePlayer1 = game.getPlayer1().getCurrentDecision();
			movePlayer2 = game.getPlayer2().getCurrentDecision();
			game.getPlayer1().setCurrentDecision(null);
			game.getPlayer2().setCurrentDecision(null);
			notifyAll();
		} 
}
