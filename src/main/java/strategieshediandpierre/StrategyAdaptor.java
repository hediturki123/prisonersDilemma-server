package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;
import strategies.Action;
import strategies.Strategy;

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
					{
						if(strat.askAction(Action.COLLABORER) == Action.COLLABORER) {
							player.action(Decision.COOPERATE,0);
						}else {
							player.action(Decision.BETRAY,0);
						}
					}
					else {
						if(strat.askAction(Action.TRAHIR) == Action.COLLABORER) {
							player.action(Decision.COOPERATE,0);
						}else {
							player.action(Decision.BETRAY,0);
						}
					}
				}
				if (player.getId() == game.getPlayer2().getId()) {
					if(lastRound.getMovePlayer1() == Decision.COOPERATE)
					{
						if(strat.askAction(Action.COLLABORER) == Action.COLLABORER) {
							player.action(Decision.COOPERATE,0);
						}else {
							player.action(Decision.BETRAY,0);
						}
					}
					else {
						if(strat.askAction(Action.TRAHIR) == Action.COLLABORER) {
							player.action(Decision.COOPERATE,0);
						} else {
							player.action(Decision.BETRAY,0);
						}
					}
				}
			}
		}
	}
}
