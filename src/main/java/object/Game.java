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
	
	private int id;
	
	private List<Round> rounds;
	
	private Player player1;
	
	private Player player2;
	
	public Game() {
		incrId();
	}
	
	public Game(Player player1, Player player2) {
		incrId();
		setPlayer1(player1);
		setPlayer2(player2);
	}
	
	public void calculateScore() {
		
	}
	
	public void displayScore() {
		
	}
	
	public void play() {
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = player1.createGame(0);
		player2.joinGame(game);
	}

	public void incrId() {
		this.id++;
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
	
	public List<Player> getPlayers() {
		return Arrays.asList(player1, player2);
	}

}