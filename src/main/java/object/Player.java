package object;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import strategies.AlwaysBetray;
import strategies.AlwaysCollaborate;
import strategies.GiveGive;
import strategies.Strategy;
import strategiesHediAndPierre.*;

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
	private boolean hasLeftTheGame;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	public SseEmitter sseEmitter;
	
	public Player() {
		this.id = staticId++;
		this.score = 0;
		this.hasLeftTheGame = false;
	}
	
	public Game createGame(int nbTurns) {
		Game game = new Game(nbTurns);
		game.setPlayer1(this); 
		return game;
	}
	
	public void joinGame(Game game) {
		if (game.getPlayer1().getId() != this.id && game.getPlayer2() == null) {
			game.setPlayer2(this);
		}
	}
	
	public void action(Decision decision, int strategyCode) {
		if(strategyCode == 0) {
			this.currentDecision = decision;
		} else {
			Strategy strategy = null;
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
						strategy = new AlwaysCollaborate();
						break;
					case 10:
						strategy = new AlwaysBetray();
						break;
					case 11:
						strategy = new GiveGive();
						break;
					default : 
						break;
				}
			}
			if(this.strategy != null) {
				this.strategy.action(this);
			}else {
				StrategyAdaptor strat = new StrategyAdaptor();
				strat.execute(this, strategy);
			}
		}
	}
	
	
//	public void sendSseEventsToUi(boolean notification) {
//		 List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
//		 Controller.emitters.forEach((SseEmitter emitter) -> {
//	            try {
//	                emitter.send(notification, MediaType.APPLICATION_JSON);
//	            } catch (IOException e) {
//	                emitter.complete();
//	                sseEmitterListToRemove.add(emitter);
//	                e.printStackTrace();
//	            }
//	        });
//		 	System.out.println("---------------------------------------------->" + Controller.emitters);
//	        Controller.emitters.removeAll(sseEmitterListToRemove);
//	}
	
}