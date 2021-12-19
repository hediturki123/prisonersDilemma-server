package strategieshediandpierre;

import java.util.List;
import java.util.Random;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public final class GiveGiveRandomStrategy extends StrategyHediAndPierreImpl implements StrategyHediAndPierre {

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
			if(!isGameFound(player)) {
				return cooperateActionPlayer(player);
			}
			else {
				Game game = RestServer.getGames().get(getIndex());
				List<Round> rounds = RestServer.getGames().get(getIndex()).getHistory();
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
