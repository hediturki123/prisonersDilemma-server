package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public class ResentfulStrategy extends StrategyHediAndPierreImpl implements StrategyHediAndPierre {
	
	@Override
	public Decision action(Player player) {
		boolean isGameFound = searchGame(player);
		
		if(!isGameFound) {
			player.setCurrentDecision(Decision.COOPERATE);
			return Decision.COOPERATE;
		} else{
			Game game = RestServer.getGames().get(getIndex());
			List<Round> rounds = game.getHistory();
			if(rounds != null && !rounds.isEmpty()) {
				int indexRound = 0;
				if (player.getId() == game.getPlayer1().getId()) {
					while(indexRound < rounds.size() && rounds.get(indexRound).getMovePlayer2() != Decision.BETRAY) {
						indexRound++;
					}
					if(indexRound != rounds.size()) {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}
				}
				else if (player.getId() == game.getPlayer2().getId()) {
					while(indexRound < rounds.size() && rounds.get(indexRound).getMovePlayer1() != Decision.BETRAY) {
						indexRound++;
					}
					if(indexRound != rounds.size()) {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}
				}
			} else {
				player.setCurrentDecision(Decision.COOPERATE);
				return Decision.COOPERATE;
			}
		}
		player.setCurrentDecision(Decision.COOPERATE);
		return Decision.COOPERATE;
	}
}
