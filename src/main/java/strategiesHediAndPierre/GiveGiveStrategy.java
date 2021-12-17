package strategiesHediAndPierre;

import java.util.List;

import com.example.RestServer;

import object.Decision;
import object.Game;
import object.Player;
import object.Round;

public final class GiveGiveStrategy implements StrategyHediAndPierre {

    @Override
	public Decision action(Player player) {
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
			return Decision.COOPERATE;
		}
	}

}
