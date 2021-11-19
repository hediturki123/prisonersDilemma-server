package strategies;

import java.util.List;

import object.Player;
import object.Round;

public final class GiveGiveStrategy implements Strategy {

	@Override
	public void action(Player player) {
		List<Round> rounds = player.getGame().getHistory();
		Round lastRound = rounds.get(rounds.size()-1);
		if(player.getId()==player.getGame().getPlayer1().getId())
		{
			player.setCurrentDecision(lastRound.getMovePlayer2());;			
		}
		if(player.getId()==player.getGame().getPlayer2().getId()) {
			player.setCurrentDecision(lastRound.getMovePlayer1());
		}
	}

}
