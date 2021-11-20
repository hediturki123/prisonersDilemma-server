package object;

import com.example.RestServer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import strategies.BetrayStrategy;
import strategies.CooperateStrategy;
import strategies.GiveGiveRandomStrategy;
import strategies.GiveGiveStrategy;
import strategies.Strategy;

@Getter
@Setter
public class Player {
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static int staticId = 1;
	
	//private Game game;
	
	private int id;
	
	private int score;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Strategy strategy;
	
	private Decision currentDecision;
	
	private boolean havePlayed = false;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private boolean hasLeftTheGame;
	
	public Player() {
		incrId();
		this.score = 0;
		//this.game = null;
		this.hasLeftTheGame = false;
	}
	
	public Game createGame(int nbTurns) {
		Game game = new Game(nbTurns);
		game.setPlayer1(this);
		RestServer.games.add(game);
		//setGame(game);
		return game;
	}
	
	public void joinGame(Game game) {
		if (game.getPlayer1().getId() != this.id && game.getPlayer2() == null) {
			game.setPlayer2(this);
			//setGame(game);
			//game.launch();
		}
	}
	
	public void action(Decision decision, int strategyCode) {
		if(strategyCode == 0) {
			this.currentDecision = decision;
		}
		else {
			if(decision == Decision.GIVEUP) {
				switch(strategyCode) {
				case 1:
					this.strategy = new GiveGiveStrategy();
					break;
				case 2:
					this.strategy = new GiveGiveRandomStrategy();
					break;
				case 3:
					this.strategy = new CooperateStrategy();
					break;
				case 4:
					this.strategy = new BetrayStrategy();
					break;
				}
			}
			strategy.action(this);
		}
	}
	
	public void incrId() {
		this.id = staticId++;
	}
	
}