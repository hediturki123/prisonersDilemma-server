package object;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import object.Decision;
import object.Game;
import object.Player;
import object.Round;

class GameTest {

	private Game game;
	private Player player1;
	private Player player2;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new Game(10);
		player1 = new Player();
		player2 = new Player();
		game.setPlayer1(player1);
		game.setPlayer2(player2);
	}

	@Test
	void testGameInt() {
		assertEquals(true, game.getNbTurns()==10);
	}

	@Test
	void testCalculateScoreBothCooperate() {
		Round round1 = new Round();
		round1.setMovePlayer1(Decision.COOPERATE);
		round1.setMovePlayer2(Decision.COOPERATE);
		game.calculateScore(round1);
		assertEquals(true, player1.getScore() == 3);
		assertEquals(true, player2.getScore() == 3);
	}

	@Test
	void testCalculateScoreBothBetray() {
		Round round1 = new Round();
		round1.setMovePlayer1(Decision.BETRAY);
		round1.setMovePlayer2(Decision.BETRAY);
		game.calculateScore(round1);
		assertEquals(true, player1.getScore() == 1);
		assertEquals(true, player2.getScore() == 1);
	}
	
	@Test
	void testCalculateScoreCooperateBetray() {
		Round round1 = new Round();
		round1.setMovePlayer1(Decision.COOPERATE);
		round1.setMovePlayer2(Decision.BETRAY);
		game.calculateScore(round1);
		assertEquals(true, player1.getScore() == 0);
		assertEquals(true, player2.getScore() == 5);
	}
	
	@Test
	void testFindPlayerById() {
		assertEquals(true, game.findPlayerById(game.getPlayer1().getId()).equals(player1));
	}

	@Test
	void testAllPlayers() {
		List<Player> list = Arrays.asList(player1,player2);
		boolean b = list.get(0).equals(game.allPlayers().get(0)) && list.get(1).equals(game.allPlayers().get(1));
		assertEquals(true, b);
	}

}
