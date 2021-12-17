package strategiesHediAndPierre;

import object.Decision;
import object.Player;

public class BetrayStrategy implements StrategyHediAndPierre {
	
	@Override
	public Decision action(Player player) {
		player.setCurrentDecision(Decision.BETRAY);
		return Decision.BETRAY;
	}

}
