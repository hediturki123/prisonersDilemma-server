package strategieshediandpierre;

import java.util.List;
import java.util.Random;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public final class GiveGiveRandomStrategy implements StrategyHediAndPierre {

	Random random = new Random();
	
	@Override
	public Decision action(Player player) {
		boolean isDecisionRandom = (1 + random.nextInt(100)) > 80; // int between 1 and 100
		if (isDecisionRandom) {
			boolean isRandomDecisionCooperate = random.nextBoolean();
			if(isRandomDecisionCooperate) {	
				player.setCurrentDecision(Decision.COOPERATE);
				return Decision.COOPERATE;
			} else {
				player.setCurrentDecision(Decision.BETRAY);
				return Decision.BETRAY;
			}
		}
		else {
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
			}
			else {
				Game game = RestServer.getGames().get(index);
				List<Round> rounds = RestServer.getGames().get(index).getHistory();
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
			}
			return Decision.COOPERATE;
		}
	}

}
