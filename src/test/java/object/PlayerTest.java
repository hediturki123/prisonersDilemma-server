package object;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.RestServer;

class PlayerTest {

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
	
	@Test
	void testActionBETRAY() {
		player.action(Decision.BETRAY,0);
		assertEquals(true, player.getCurrentDecision() == Decision.BETRAY);
	}
	
	@Test
	void testActionNotBETRAY() {
		player.action(Decision.BETRAY,0);
		assertEquals(true, player.getCurrentDecision() != Decision.COOPERATE && player.getCurrentDecision() != Decision.GIVEUP);
	}
	
	@Test
	void testActionGIVEUPGiveGive() {
		Game game = player.createGame(10);
		Player player2 = new Player();
		player2.joinGame(game);
		
		Round round = new Round();
		round.setMovePlayer2(Decision.BETRAY);
		round.setMovePlayer1(Decision.COOPERATE);
		
		List<Round> list = new ArrayList<>();
		list.add(round);
		
		game.setHistory(list);
		
		player.action(Decision.GIVEUP,1);
		assertEquals(true, player.getCurrentDecision() ==  Decision.BETRAY);
	}
	
	//Not really useful to test since it's random and we can't predict this strategy action
	@Test
	void testActionGIVEUPGiveGiveRandom() {
		Game game = player.createGame(10);
		Player player2 = new Player();
		player2.joinGame(game);
		
		Round round = new Round();
		round.setMovePlayer2(Decision.BETRAY);
		round.setMovePlayer1(Decision.COOPERATE);
		
		List<Round> list = new ArrayList<>();
		list.add(round);
		
		game.setHistory(list);
		
		player.action(Decision.GIVEUP,2);
		assertEquals(true, player.getCurrentDecision() !=  Decision.GIVEUP);
	}
	
	@Test
	void testActionGIVEUPCooperate() {
		Game game = player.createGame(10);
		Player player2 = new Player();
		player2.joinGame(game);
		
		Round round = new Round();
		round.setMovePlayer2(Decision.COOPERATE);
		round.setMovePlayer1(Decision.COOPERATE);
		
		List<Round> list = new ArrayList<>();
		list.add(round);
		
		game.setHistory(list);
		
		player.action(Decision.GIVEUP,3);
		assertEquals(true, player.getCurrentDecision() ==  Decision.COOPERATE);
	}
	
	@Test
	void testActionGIVEUPBetray() {
		Game game = player.createGame(10);
		Player player2 = new Player();
		player2.joinGame(game);
		
		Round round = new Round();
		round.setMovePlayer2(Decision.BETRAY);
		round.setMovePlayer1(Decision.COOPERATE);
		
		List<Round> list = new ArrayList<>();
		list.add(round);
		
		game.setHistory(list);
		
		player.action(Decision.GIVEUP,4);
		assertEquals(true, player.getCurrentDecision() ==  Decision.BETRAY);
	}
	
	@Test
	void testCreateGame() {
		RestServer.games.clear();
		Game game = player.createGame(10);
		assertEquals(true, RestServer.games.get(0).equals(game));
		assertEquals(true, game.getNbTurns()==10);
		assertEquals(true, player.getId()==RestServer.games.get(0).getPlayer1().getId());
	}
	
	@Test
	void testCreateGameButCompareWithOther() {
		RestServer.games.clear();
		Game game = player.createGame(10);
		player.createGame(7);
		assertEquals(false, RestServer.games.get(1).equals(game));
		assertEquals(false, game.getId()==RestServer.games.get(1).getId());
	}
}
