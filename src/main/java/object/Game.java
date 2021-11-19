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
		switch(round.getMovePlayer1()) {
			case COOPERATE :
				switch(round.getMovePlayer2()) {
					case COOPERATE :
						player1.setScore(player1.getScore() + 3);
						player2.setScore(player2.getScore() + 3);
						break;
					case BETRAY :
						player2.setScore(player2.getScore() + 5);
						break;
					default :
						break;
				}
				break;
			case BETRAY :
				switch(round.getMovePlayer2()) {
					case COOPERATE :
						player1.setScore(player1.getScore() + 5);
						break;
					case BETRAY :
						player1.setScore(player1.getScore() + 1);
						player2.setScore(player2.getScore() + 1);
						break;
					default :
						break;
				}
				break;
			default :
				break;
		}
	}

	synchronized public void launch() {
		Round round;
		Thread playRound;
		for (int i = 0; i < nbTurns; i++) {
			round = new Round();
			playRound = new RoundThread(round, this);
			playRound.start();
			calculateScore(round);
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