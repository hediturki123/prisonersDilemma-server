package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;
import strategies.Action;
import strategies.Strategy;

public class StrategyAdaptor extends StrategyHediAndPierreImpl{

	public void execute(Player player, Strategy strat) {
		
		if(!isGameFound(player)) {
			strat.askAction(null);
		} else {
			Game game = RestServer.getGames().get(getIndex());
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
			else {
				if(strat.askAction(null) == Action.COLLABORER) {
					player.action(Decision.COOPERATE,0);
				} else {
					player.action(Decision.BETRAY,0);
				}
			}
		}
	}
}
