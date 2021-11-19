package strategies;

import object.Decision;
import object.Player;

public class BetrayStrategy implements Strategy {
	
	@Override
	public void action(Player player) {
		player.setCurrentDecision(Decision.BETRAY);
	}

}
