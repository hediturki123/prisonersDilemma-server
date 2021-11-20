package strategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import object.Decision;
import object.Player;

class CooperateStrategyTest {

	private Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		player = new Player();
	}
	
	@Test
	void testActionCOOPERATE() {
		player.action(Decision.COOPERATE,0);
		assertEquals(true, player.getCurrentDecision() == Decision.COOPERATE);
	}

	@Test
	void testActionNotCOOPERATE() {
		player.action(Decision.COOPERATE,0);
		assertEquals(true, player.getCurrentDecision() != Decision.BETRAY && player.getCurrentDecision() != Decision.GIVEUP);
	}
}
