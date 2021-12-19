package strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

class GiveGiveStrategyTest {

	private Player player1;
	private Player player2;
	private Game game;
	
	@BeforeEach
	void setUp() throws Exception {
		player1 = new Player();
		player1.sseEmitter = new SseEmitter();
		player2 = new Player();
		player2.sseEmitter = new SseEmitter();
		
		game = new Game(10);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
	}
	
	@Test
	void testDecisionStrategyGIVEGIVE() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 1);
		game.launch();
		assertEquals(true, player2.getCurrentDecision() == null);
	}

	@Test
	void testOtherPlayerBetrayActionStrategyCOOPERATE() {
		Round round1 = new Round();
		round1.setMovePlayer1(Decision.BETRAY);
		round1.setMovePlayer2(Decision.COOPERATE);
		game.getHistory().add(round1);
		
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 1);
		game.launch();
		Round lastRound = game.getHistory().get(game.getHistory().size()-1);
		assertEquals(Decision.BETRAY, lastRound.getMovePlayer2());
	}
	
	@Test
	void testOtherPlayerCooperateActionStrategyCOOPERATE() {
		Round round1 = new Round();
		round1.setMovePlayer1(Decision.COOPERATE);
		round1.setMovePlayer2(Decision.COOPERATE);
		game.getHistory().add(round1);
		
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 1);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testActionNotCOOPERATE() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 1);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() != Decision.BETRAY && lastRound.getMovePlayer2() != Decision.GIVEUP);
	}
}
