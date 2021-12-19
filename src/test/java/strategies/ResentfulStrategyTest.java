package strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

class ResentfulStrategyTest {
	
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
	void testDecisionStrategyRESENTFUL() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		assertEquals(true, player2.getCurrentDecision() == null);
	}

	@Test
	void testRound1ActionStrategyRESENTFUL() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testRound2WithOtherPlayerCooperateActionStrategyRESENTFUL() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testRound2WithOtherPlayerBetrayActionStrategyRESENTFUL() {
		player1.action(Decision.BETRAY, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.BETRAY);
	}
	
	@Test
	void testRound3WithOtherPlayerBetrayActionStrategyRESENTFUL() {
		player1.action(Decision.BETRAY, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		Round antepenultimateRound = game.getHistory().get(1);
		Round lastRound = game.getHistory().get(2);
		assertEquals(Decision.BETRAY
				, antepenultimateRound.getMovePlayer2());
		assertEquals(true, lastRound.getMovePlayer2() == Decision.BETRAY);
	}

	
	@Test
	void testRound1ActionNotRESENTFUL() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 8);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() != Decision.BETRAY && lastRound.getMovePlayer2() != Decision.GIVEUP);
	}
}
