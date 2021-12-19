package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public class PeriodicKind implements StrategyHediAndPierre {

	@Override
	public Decision action(Player player) {
    	int index = 0;
		boolean isGameFound = false;
		while(index < RestServer.getGames().size() && !isGameFound)
		{
			if(RestServer.getGames().get(index).getPlayer2() != null 
					&& (RestServer.getGames().get(index).getPlayer1().getId() == player.getId() 
					|| RestServer.getGames().get(index).getPlayer2().getId() == player.getId()))
			{
				isGameFound = true;
				index--;
			}
			else if(RestServer.getGames().get(index).getPlayer1().getId() == player.getId())
			{
				isGameFound = true;
				index--;
			}
			index++;
		}
		
		if(!isGameFound) {
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		} else {
			Game game = RestServer.getGames().get(index);
			List<Round> rounds = game.getHistory();
			if(rounds != null && rounds.size() > 1) {
				Round lastRound = rounds.get(rounds.size() - 1);
				Round antepenultimateRound = rounds.get(rounds.size() - 2);
				if (player.getId() == game.getPlayer1().getId()) {
					if(lastRound.getMovePlayer1() == Decision.COOPERATE
							&& antepenultimateRound.getMovePlayer1() == Decision.COOPERATE) {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}else {
						player.setCurrentDecision(Decision.COOPERATE);
						return Decision.COOPERATE;
					}
				}
				if (player.getId() == game.getPlayer2().getId()) {
					if(lastRound.getMovePlayer2() == Decision.COOPERATE
							&& antepenultimateRound.getMovePlayer2() == Decision.COOPERATE) {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}else {
						player.setCurrentDecision(Decision.COOPERATE);
						return Decision.COOPERATE;
					}
				}
			}else {
				player.setCurrentDecision(Decision.COOPERATE);
				return Decision.COOPERATE;
			}
			return Decision.COOPERATE;
		}
	}
}
