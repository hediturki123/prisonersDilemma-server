package object;

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
	
	public Player() {
		incrId();
		this.score = 0;
	}
	
	public Game createGame(int nbTurns) {
		return null;
	}
	
	public void joinGame(Game game) {
		
	}
	
	public Decision action() {
		return null;
	}
	
	public void incrId() {
		this.id = staticId++;
	}
	
	public void incrScore() {
		this.score++;
	}

}