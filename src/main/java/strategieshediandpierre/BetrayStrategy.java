package strategieshediandpierre;

import game.Decision;
import game.Player;

public class BetrayStrategy implements StrategyHediAndPierre {
	
	@Override
	public Decision action(Player player) {
		player.setCurrentDecision(Decision.BETRAY);
		return Decision.BETRAY;
	}

}
