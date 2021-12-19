package strategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Decision;
import game.Player;

class BetrayStrategyTest {

	private Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		player = new Player();
	}
	
	@Test
	void testActionBETRAY() {
		player.action(Decision.BETRAY, 0);
		assertEquals(true, player.getCurrentDecision() == Decision.BETRAY);
	}
	
	@Test
	void testActionNotBETRAY() {
		player.action(Decision.BETRAY, 0);
		assertEquals(true, player.getCurrentDecision() != Decision.COOPERATE && player.getCurrentDecision() != Decision.GIVEUP);
	}

}
