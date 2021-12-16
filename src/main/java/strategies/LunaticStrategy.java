package strategies;

import java.util.Random;
import object.Decision;
import object.Player;

public class LunaticStrategy implements StrategyHediAndPierre {

	@Override
	public void action(Player player) {
		Random random = new Random();
		boolean isDecisionCooperate = random.nextBoolean();
		if (isDecisionCooperate) {
			player.setCurrentDecision(Decision.COOPERATE);
		} else {
			player.setCurrentDecision(Decision.BETRAY);
		}
	}

}
