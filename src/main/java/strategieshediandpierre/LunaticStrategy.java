package strategieshediandpierre;

import java.util.Random;

import game.Decision;
import game.Player;

public class LunaticStrategy implements StrategyHediAndPierre {
	
	Random random = new Random();

	@Override
	public Decision action(Player player) {
		boolean isDecisionCooperate = random.nextBoolean();
		if (isDecisionCooperate) {
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		} else {
			player.setCurrentDecision(Decision.BETRAY);
			return Decision.BETRAY;
		}
	}

}
