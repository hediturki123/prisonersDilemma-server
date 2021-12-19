package strategieshediandpierre;

import game.Decision;
import game.Player;

public class CooperateStrategy implements StrategyHediAndPierre {

	@Override
	public Decision action(Player player) {
		player.setCurrentDecision(Decision.COOPERATE);
		return Decision.COOPERATE;
	}

}
