package strategies;

import java.util.List;

import com.example.RestServer;

import object.Decision;
import object.Game;
import object.Player;
import object.Round;

public final class GiveGiveStrategy implements Strategy {

    @Override
	public void action(Player player) {
    	int index = 0;
		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer2() != null && (RestServer.getGames().get(index).getPlayer1().getId() != player.getId() && RestServer.getGames().get(index).getPlayer2().getId() != player.getId())) {
			index++;
		}
		if(index == RestServer.getGames().size()) {
			player.setCurrentDecision(Decision.COOPERATE);
		}else {
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
