package strategies;

import java.util.Random;

import object.Decision;
import object.Player;

public final class GiveGiveRandomStrategy implements Strategy {

	Random random = new Random();
	
	@Override
	public void action(Player player) {
		boolean isDecisionRandom = (1 + random.nextInt(100)) > 80; // int between 1 and 100
		if (isDecisionRandom) {
			boolean isRandomDecisionCooperate = random.nextBoolean();
			if(isRandomDecisionCooperate) {	
				player.setCurrentDecision(Decision.COOPERATE);
			} else {
				player.setCurrentDecision(Decision.BETRAY);
			}
		}
//		else {
//			List<Round> rounds = player.getGame().getHistory();
//			Round lastRound = rounds.get(rounds.size() - 1);
//			
//			if (player.getId() == player.getGame().getPlayer1().getId()) {
//				player.setCurrentDecision(lastRound.getMovePlayer2());			
//			}
//			if (player.getId() == player.getGame().getPlayer2().getId()) {
//				player.setCurrentDecision(lastRound.getMovePlayer1());
//			}
//		}
	}

}
