package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public class PeriodicMean extends StrategyHediAndPierreImpl implements StrategyHediAndPierre {

	@Override
	public Decision action(Player player) {
		boolean isGameFound = searchGame(player);
		
		if(!isGameFound) {
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		}else {
			Game game = RestServer.getGames().get(getIndex());
			List<Round> rounds = game.getHistory();
			if(rounds != null && rounds.size() > 1) {
				Round lastRound = rounds.get(rounds.size() - 1);
				Round antepenultimateRound = rounds.get(rounds.size() - 2);
				if (player.getId() == game.getPlayer1().getId()) {
					if(lastRound.getMovePlayer1() == Decision.BETRAY
							&& antepenultimateRound.getMovePlayer1() == Decision.BETRAY) {
						player.setCurrentDecision(Decision.COOPERATE);
						return Decision.COOPERATE;
					}else {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}
				}
				if (player.getId() == game.getPlayer2().getId()) {
					if(lastRound.getMovePlayer2() == Decision.BETRAY
							&& antepenultimateRound.getMovePlayer2() == Decision.BETRAY) {
						player.setCurrentDecision(Decision.COOPERATE);
						return Decision.COOPERATE;
					}else {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}
				}
			}else {
				player.setCurrentDecision(Decision.BETRAY);
				return Decision.BETRAY;
			}
			player.setCurrentDecision(Decision.BETRAY);
			return Decision.BETRAY;
		}
	}
}
