package strategies;

import object.Decision;
import object.Player;

public class CooperateStrategy implements StrategyHediAndPierre {

	@Override
	public void action(Player player) {
		player.setCurrentDecision(Decision.COOPERATE);
	}

}
