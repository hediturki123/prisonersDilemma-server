package game;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import strategies.AlwaysBetray;
import strategies.AlwaysCollaborate;
import strategies.GiveGive;
import strategies.Strategy;
import strategieshediandpierre.*;
/**
 * Classe correspondante à un joueur
 */
@Getter
@Setter
public class Player {
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static int staticId = 1;
		
	private int id;
	
	private int score;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private StrategyHediAndPierre strategy;
	
	private Decision currentDecision;
	
	private boolean havePlayed = false;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	public boolean hasLeftTheGame;

	private int strategyCode;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	public SseEmitter sseEmitter;
	
	
	public Player() {
		this.id = staticId++;
		this.score = 0;
		this.hasLeftTheGame = false;
	}
	/**
	 * Méthode permettant de créer unne partie
	 * @param nbTurns le nombre de tours que le joueur aura choisi
	 * @return la partie créée
	 */
	public Game createGame(int nbTurns) {
		Game game = new Game(nbTurns);
		game.setPlayer1(this); 
		return game;
	}
	/**
	 * Méthode permettant de rejoindre une partie
	 * @param game la partie que je joueur veut rejoindre
	 */
	public void joinGame(Game game) {
		if (game.getPlayer1().getId() != this.id && game.getPlayer2() == null) {
			game.setPlayer2(this);
		}
	}
	
	/**
	 * Méthode permettant d'appliquer une décision prise par le joueur ou d'appliquer une stratégie.
	 * Les stratégies implémentées sont celles que nous avons implémentées nous même et celles de 
	 * Romain et Yann.
	 * 
	 * @param decision la décision prise par le joueur
	 * @param strategyCode le code de la stratégie que le joueur à choisi
	 */
	public void action(Decision decision, int strategyCode) {
		if(strategyCode == 0) {
			this.currentDecision = decision;
		} else {
			Strategy strategyRomainAndYann = null;
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
					case 5:
						this.strategy = new LunaticStrategy();
						break;
					case 6:
						this.strategy = new PeriodicKind();
						break;
					case 7:
						this.strategy = new PeriodicMean();
						break;
					case 8:
						this.strategy = new ResentfulStrategy();
						break;
					case 9:
						strategyRomainAndYann = new AlwaysCollaborate();
						break;
					case 10:
						strategyRomainAndYann = new AlwaysBetray();
						break;
					case 11:
						strategyRomainAndYann = new GiveGive();
						break;
					default : 
						break;
				}
			}
			if(this.strategy != null) {
				this.strategy.action(this);
			} else {
				StrategyAdaptor strat = new StrategyAdaptor();
				strat.execute(this, strategyRomainAndYann);
			}
		}
	}
	
}