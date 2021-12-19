package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public class PeriodicMean implements StrategyHediAndPierre {

	@Override
	public Decision action(Player player) {
    	int index = 0;
    	if(RestServer.getGames().get(index).getPlayer2() != null)
    	{
    		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer1().getId() != player.getId() && RestServer.getGames().get(index).getPlayer2().getId() != player.getId()) {
    			index++;
    		}
    	}else {
    		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer1().getId() != player.getId()) {
    			index++;
    		}
    	}
		
		if(index == RestServer.getGames().size()) {
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		} else {
			Game game = RestServer.getGames().get(index);
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
