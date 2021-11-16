package object;

import com.example.RestServer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import strategies.Strategy;

@Getter
@Setter
@AllArgsConstructor
public class Player {
	
	private static int staticId = 1;
	
	private int id;
	
	private int score;
	
	private Strategy strategy;
	
	private Decision currentDecision;
	
	public Player() {
		incrId();
		this.score = 0;
	}
	
	public Game createGame(int nbTurns) {
		Game game = new Game(nbTurns);
		game.setPlayer1(this);
		RestServer.games.add(game);
		return game;
	}
	
	public void joinGame(Game game) {
		if (game.getPlayer1().getId() != this.id) {
			game.setPlayer2(this);
			game.launch();
		}
	}
	
	public void action(Game game, Decision decision) {
		switch(decision) {
		case COOPERATE :
		case BETRAY :
			break;
		case GIVEUP :
			break;
		}
	}
	
	public void incrId() {
		this.id = staticId++;
	}
	
	public void incrScore() {
		this.score++;
	}

}