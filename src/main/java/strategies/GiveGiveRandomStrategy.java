package strategies;

import java.util.List;
import java.util.Random;

import object.Decision;
import object.Player;
import object.Round;

public final class GiveGiveRandomStrategy implements Strategy {

	@Override
	public void action(Player player) {
		
		Random rand = new Random();
		boolean isDecisionRandom = rand.nextInt(1, 101) > 80; // int between 1 and 100
		if(isDecisionRandom)
		{
			boolean isRandomDecisionCooperate = rand.nextBoolean();
			
			if(isRandomDecisionCooperate)
				player.setCurrentDecision(Decision.COOPERATE);
			else
				player.setCurrentDecision(Decision.BETRAY);
		}else {
			List<Round> rounds = player.getGame().getHistory();
			Round lastRound = rounds.get(rounds.size()-1);
			
			if(player.getId()==player.getGame().getPlayer1().getId())
			{
				player.setCurrentDecision(lastRound.getMovePlayer2());			
			}
			if(player.getId()==player.getGame().getPlayer2().getId()) {
				player.setCurrentDecision(lastRound.getMovePlayer1());
			}
		}
	}

}
