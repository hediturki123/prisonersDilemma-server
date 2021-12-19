package strategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

class BetrayStrategyTest {

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
	void testDecisionStrategyBETRAY() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 4);
		game.launch();
		assertEquals(true, player2.getCurrentDecision() == null);
	}
	
	@Test
	void testActionStrategyBETRAY() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 4);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.BETRAY);
	}
	
	@Test
	void testActionNotCOOPERATE() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 4);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() != Decision.COOPERATE && lastRound.getMovePlayer2() != Decision.GIVEUP);
	}

}
