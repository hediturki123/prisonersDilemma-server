package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public final class GiveGiveStrategy implements StrategyHediAndPierre {

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
