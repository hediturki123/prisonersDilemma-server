package strategies;

import java.util.List;
import java.util.Random;

import com.example.RestServer;

import object.Decision;
import object.Game;
import object.Player;
import object.Round;

public final class GiveGiveRandomStrategy implements Strategy {

	Random random = new Random();
	
	@Override
	public void action(Player player) {
		boolean isDecisionRandom = (1 + random.nextInt(100)) > 80; // int between 1 and 100
		if (isDecisionRandom) {
			boolean isRandomDecisionCooperate = random.nextBoolean();
			if(isRandomDecisionCooperate) {	
				player.setCurrentDecision(Decision.COOPERATE);
			} else {
				player.setCurrentDecision(Decision.BETRAY);
			}
		}
		else {
			int index = 0;
			while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer2() != null && (RestServer.getGames().get(index).getPlayer1().getId() != player.getId() && RestServer.getGames().get(index).getPlayer2().getId() != player.getId())) {
				index++;
			}
			if(index == RestServer.getGames().size()) {
				player.setCurrentDecision(Decision.COOPERATE);
			}
			else {
				Game game = RestServer.getGames().get(index);
				List<Round> rounds = RestServer.getGames().get(index).getHistory();
				if(rounds != null) {
					Round lastRound = rounds.get(rounds.size() - 1);
					if (player.getId() == game.getPlayer1().getId()) {
						player.setCurrentDecision(lastRound.getMovePlayer2());			
					}
					if (player.getId() == game.getPlayer2().getId()) {
						player.setCurrentDecision(lastRound.getMovePlayer1());
					}
				}
			}
		}
	}

}
