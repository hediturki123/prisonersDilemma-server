package strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

public class PeriodicKindTest {

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
	void testDecisionStrategyPERIODICKIND() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		assertEquals(true, player2.getCurrentDecision() == null);
	}

	@Test
	void testRound1ActionStrategyPERIODICKIND() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testRound2ActionStrategyPERIODICKIND() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testRound3ActionStrategyPERIODICKIND() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		Round lastRound = game.getHistory().get(2);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.BETRAY);
	}

	
	@Test
	void testRound1ActionNotPERIODICKIND() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 6);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() != Decision.BETRAY && lastRound.getMovePlayer2() != Decision.GIVEUP);
	}
}
