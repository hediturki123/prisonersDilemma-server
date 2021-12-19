package strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import game.Decision;
import game.Game;
import game.Player;
import game.Round;

class StrategyAdaptorTest {
	
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
	void testAdaptorDecisionStrategyRESENTFUL() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 9);
		game.launch();
		assertEquals(true, player2.getCurrentDecision() == null);
	}
	
	@Test
	void testAdaptorActionStrategyALWAYSCOLLABORATE() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 9);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testAdaptorActionStrategyALWAYSBETRAY() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 10);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.BETRAY);
	}
	
	@Test
	void testAdaptorActionStrategyGIVEGIVE() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 11);
		game.launch();
		Round lastRound = game.getHistory().get(0);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testAdaptorOtherPlayerBetrayActionStrategyGIVEGIVE() {
		player1.action(Decision.BETRAY, 0);
		player2.action(Decision.GIVEUP, 11);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 11);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.BETRAY);
	}
	
	@Test
	void testAdaptorOtherPlayerCooperateActionStrategyGIVEGIVE() {
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 11);
		game.launch();
		player1.action(Decision.COOPERATE, 0);
		player2.action(Decision.GIVEUP, 11);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer2() == Decision.COOPERATE);
	}
	
	@Test
	void testAdaptorOtherPlayerCooperateOtherPlayerActionStrategyGIVEGIVE() {
		player1.action(Decision.GIVEUP, 11);
		player2.action(Decision.COOPERATE, 0);
		game.launch();
		player1.action(Decision.GIVEUP, 11);
		player2.action(Decision.COOPERATE, 0);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer1() == Decision.COOPERATE);
	}
	
	@Test
	void testAdaptorOtherPlayerBetrayOtherPlayerActionStrategyALWAYSBETRAY() {
		player1.action(Decision.GIVEUP, 11);
		player2.action(Decision.BETRAY, 0);
		game.launch();
		player1.action(Decision.GIVEUP, 11);
		player2.action(Decision.COOPERATE, 0);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer1() == Decision.BETRAY);
	}
	
	@Test
	void testAdaptorOtherPlayerCooperateOtherPlayerActionStrategyALWAYSBETRAY() {
		player1.action(Decision.GIVEUP, 10);
		player2.action(Decision.COOPERATE, 0);
		game.launch();
		player1.action(Decision.GIVEUP, 10);
		player2.action(Decision.COOPERATE, 0);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer1() == Decision.BETRAY);
	}
	
	@Test
	void testAdaptorOtherPlayerCooperateOtherPlayerActionStrategyALWAYSCOLLABORATE() {
		player1.action(Decision.GIVEUP, 9);
		player2.action(Decision.BETRAY, 0);
		game.launch();
		player1.action(Decision.GIVEUP, 9);
		player2.action(Decision.COOPERATE, 0);
		game.launch();
		Round lastRound = game.getHistory().get(1);
		assertEquals(true, lastRound.getMovePlayer1() == Decision.COOPERATE);
	}
}
