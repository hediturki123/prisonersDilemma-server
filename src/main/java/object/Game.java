package object;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.RestServer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Game {
	
	private static int staticId = 1;
	
	private int id;
	
	private List<Round> history;
	
	private int currentRound;
	
	private Player player1;
	
	private Player player2;
	
	private int nbTurns;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private SseEmitter sseEmitter;
	
	public Game(int nbTurns) {
		this.id = staticId++;
		setNbTurns(nbTurns);
		this.currentRound = 1;
		RestServer.addGame(this);
		this.history = new ArrayList<>();
		this.sseEmitter = new SseEmitter();	
	}
	
	public Game() {
		this.id = 0;
		setNbTurns(0);
		this.currentRound = 1;
		RestServer.addGame(this);
		this.history = new ArrayList<>();
		this.sseEmitter = new SseEmitter();		
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

	public void launch() {
		Round round;
		if (currentRound <= nbTurns) {
			round = new Round();
			round.playRound(this);
			calculateScore(round);
			history.add(round);
			try {
				getPlayer2().sseEmitter.send(this);
				getPlayer1().sseEmitter.send(this);
			} catch (IOException e) {
				getPlayer2().sseEmitter.completeWithError(e);
				getPlayer1().sseEmitter.completeWithError(e);
				e.printStackTrace();
			}
		}
	}

	public Player findPlayerById(int id) {
		if (player1.getId() == id) {
			return player1;
		} else if (player2 != null && player2.getId() == id) {
			return player2;
		} else {
			return null;
		}
	}
	
	public List<Player> allPlayers() {
		return Arrays.asList(player1, player2);
	}

}