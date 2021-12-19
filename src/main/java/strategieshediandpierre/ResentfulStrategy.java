package strategieshediandpierre;

import java.util.List;

import com.example.RestServer;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public class ResentfulStrategy implements StrategyHediAndPierre {
	
	@Override
	public Decision action(Player player) {
    	int index = 0;
    	if(RestServer.getGames().get(index).getPlayer2() != null)
    	{
    		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer1().getId() != player.getId() && RestServer.getGames().get(index).getPlayer2().getId() != player.getId()) {
    			index++;
    		}
    	}else {
    		while(index < RestServer.getGames().size() && RestServer.getGames().get(index).getPlayer1().getId() != player.getId()) {
    			index++;
    		}
    	}
		
    	player.setCurrentDecision(Decision.COOPERATE);
		if(index != RestServer.getGames().size()) {
			Game game = RestServer.getGames().get(index);
			List<Round> rounds = game.getHistory();
			if(rounds != null && rounds.size() > 0) {
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
				if (player.getId() == game.getPlayer2().getId()) {
					while(indexRound < rounds.size() && rounds.get(indexRound).getMovePlayer1() != Decision.BETRAY) {
						indexRound++;
					}
					if(indexRound != rounds.size()) {
						player.setCurrentDecision(Decision.BETRAY);
						return Decision.BETRAY;
					}
				}
			}
		}
		player.setCurrentDecision(Decision.COOPERATE);
		return Decision.COOPERATE;
	}
}