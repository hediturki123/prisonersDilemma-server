package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public final class GiveGiveStrategy extends StrategyHediAndPierreImpl implements StrategyHediAndPierre {

    @Override
	public Decision action(Player player) {
		boolean isGameFound = searchGame(player);
		
		if(!isGameFound) {
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		} else {
			Game game = RestServer.getGames().get(getIndex());
			List<Round> rounds = game.getHistory();
			if(rounds != null && !rounds.isEmpty()) {
				Round lastRound = rounds.get(rounds.size() - 1);
				if (player.getId() == game.getPlayer1().getId()) {
					player.setCurrentDecision(lastRound.getMovePlayer2());
					return lastRound.getMovePlayer2();
				}
				if (player.getId() == game.getPlayer2().getId()) {
					player.setCurrentDecision(lastRound.getMovePlayer1());
					return lastRound.getMovePlayer1();
				}
			}
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		}
	}

}
