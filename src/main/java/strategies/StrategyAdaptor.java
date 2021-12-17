package strategies;

import java.util.List;

import com.example.RestServer;

import object.Decision;
import object.Game;
import object.Player;
import object.Round;

public class StrategyAdaptor {

	public void execute(Player player, Strategy strat) {
		int index = 0;
    	if(RestServer.getGames().get(index).getPlayer2() != null) {
    		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer1().getId() != player.getId() && RestServer.getGames().get(index).getPlayer2().getId() != player.getId()) {
    			index++;
    		}
    	} else {
    		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer1().getId() != player.getId()) {
    			index++;
    		}
    	}
		
		if(index == RestServer.getGames().size()) {
			strat.askAction(null);
		} else {
			Game game = RestServer.getGames().get(index);
			List<Round> rounds = game.getHistory();
			
			if(rounds != null && !rounds.isEmpty()) {
				Round lastRound = rounds.get(rounds.size() - 1);
				if (player.getId() == game.getPlayer1().getId()) {
					if(lastRound.getMovePlayer2() == Decision.COOPERATE)
						strat.askAction(Action.COLLABORER);
					else
						strat.askAction(Action.TRAHIR);
				}
				if (player.getId() == game.getPlayer2().getId()) {
					if(lastRound.getMovePlayer1() == Decision.COOPERATE)
						strat.askAction(Action.COLLABORER);
					else
						strat.askAction(Action.TRAHIR);
				}
			}
		}
	}
}
