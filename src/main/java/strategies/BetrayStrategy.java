package strategies;

import object.Decision;
import object.Player;

public class BetrayStrategy implements StrategyHediAndPierre {
	
	@Override
	public void action(Player player) {
		player.setCurrentDecision(Decision.BETRAY);
	}

}
