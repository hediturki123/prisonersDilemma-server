package strategieshediandpierre;

import com.example.RestServer;

import game.Decision;
import game.Player;
import lombok.Getter;

@Getter
public class StrategyHediAndPierreImpl {

	private int index = 0;
	/**
	 * Méthode qui permet de rechercher une partie (écrite pour éviter de dupliquer du code)
	 * @param player le joueur
	 * @return boolean partie trouvée ou pas
	 */
	public boolean isGameFound(Player player) {
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
	
	public Decision cooperateActionPlayer(Player player) {
		player.setCurrentDecision(Decision.COOPERATE);
		return Decision.COOPERATE;
	}
}
