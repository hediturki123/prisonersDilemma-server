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
	
	private int nbTurns;
	
	public Game(int nbTurns) {
		incrId();
		setNbTurns(nbTurns);
	}
	
	public void calculateScore(Round round) {
		
	}
	
	public void displayScore(Round round) {
		
	}

	public void launch() {
		Round round;
		for (int i = 0; i < nbTurns; i++) {
			round = new Round();
			round.playRound(this);
			calculateScore(round);
			displayScore(round);
			history.add(round);
		}
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