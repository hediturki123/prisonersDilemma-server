package object;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

class RoundTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testPlayRound() {
		Game game = new Game(10);
		Player player1 = new Player();
		player1.sseEmitter = new SseEmitter();
		Player player2 = new Player();
		player2.sseEmitter = new SseEmitter();
		
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		
		player1.setCurrentDecision(Decision.COOPERATE);
		player2.setCurrentDecision(Decision.BETRAY);
		
		Round round = new Round();
		
		round.playRound(game);
		assertEquals(true, player1.getCurrentDecision() == null && player2.getCurrentDecision() == null);
		
		assertEquals(true, round.getMovePlayer1() == Decision.COOPERATE && round.getMovePlayer2() == Decision.BETRAY);
	}

}
