package object;


import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Game {
	
	private static int staticId = 1;
	
	private int id;
	
	private List<Round> history;
	
	private Player player1;
	
	private Player player2;

	
	public Game() {
		incrId();
		player1 = new Player();
		player2 = new Player();
	}
	
	public void calculateScore() {
		
	}
	
	public void displayScore() {
		
	}
	
	public void createRoundAndSaveHistory(Player player) {
	if ((player.action().equals(Decision.BETRAY)) || 
				player.action().equals(Decision.COOPERATE)){
			history.add(new Round());
		}
	}
	
	public void play() {
		Game game = player1.createGame(0);
		player2.joinGame(game);
	}

	public void incrId() {
		this.id = staticId++;
	}
	
	public Player findPlayerById(int id) {
		if (player1.getId() == id) {
			return player1;
		} else if (player2.getId() == id) {
			return player2;
		} else {
			return null;
		}
	}
	
	public List<Player> allPlayers() {
		return Arrays.asList(player1, player2);
	}

}