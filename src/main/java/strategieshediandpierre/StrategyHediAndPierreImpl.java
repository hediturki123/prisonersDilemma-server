package strategieshediandpierre;

import com.example.RestServer;

import game.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StrategyHediAndPierreImpl {

	private int index = 0;
	
	public boolean searchGame(Player player) {
		boolean isGameFound = false;
		while(index < RestServer.getGames().size() && !isGameFound) {
			if(RestServer.getGames().get(index).getPlayer2() != null 
					&& (RestServer.getGames().get(index).getPlayer1().getId() == player.getId() 
					|| RestServer.getGames().get(index).getPlayer2().getId() == player.getId()) 
				|| (RestServer.getGames().get(index).getPlayer1().getId() == player.getId())) {
				isGameFound = true;
				index--;
			}
			index++;
		}
		return isGameFound;
	}
}
