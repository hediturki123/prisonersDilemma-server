package com.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import object.Decision;
import object.Game;
import object.Player;

@SpringBootTest
class ControllerTest {

	@Autowired
	private Controller controller;
	
	
	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(controller).isNotNull();
	}
	
	@Test
	void findGameByIdTestTrue() {
		RestServer.addGame(new Game(5));
		Game game = controller.findGameById(1);
		assertEquals(RestServer.getGames().get(0).getId(), game.getId());
	}
	
	@Test
	void createGameTest() {
		ResponseEntity<Game> createGame = controller.createGame(10);
		assertEquals(RestServer.getGames().get(RestServer.getGames().size() - 1).getId(), createGame.getBody().getId());
	}
	
	@Test
	void readGameTest() {
		ResponseEntity<Game> createGame = controller.createGame(10);
		ResponseEntity<Game> readGame = controller.readGame(createGame.getBody().getId());
		assertEquals(RestServer.getGames().get(RestServer.getGames().size() - 1).getId(), readGame.getBody().getId());
	}
	
	@Test
	void readPlayerFromGameTestPlayer1() {
		ResponseEntity<Game> createGame = controller.createGame(10);
		ResponseEntity<Game> readGame = controller.readGame(createGame.getBody().getId());
		assertEquals(readGame.getBody().getPlayer1().getId(), createGame.getBody().getPlayer1().getId());
	}
	
	@Test
	void readPlayerFromGameTestPlayer2() {
		ResponseEntity<Game> createGame = controller.createGame(10);
		ResponseEntity<Game> joinGame = controller.joinGame(createGame.getBody().getId());
		ResponseEntity<Game> readGame = controller.readGame(createGame.getBody().getId());
		assertEquals(readGame.getBody().getPlayer2().getId(), joinGame.getBody().getPlayer2().getId());
	}
	
	@Test
	void readPlayerTest() {
		ResponseEntity<Game> createGame = controller.createGame(10);
		ResponseEntity<Player> readPlayer = controller.readPlayer(createGame.getBody().getId(), createGame.getBody().getPlayer1().getId());
		assertEquals(createGame.getBody().getPlayer1().getId(), readPlayer.getBody().getId());
	}
	
	@Test
	void updatePlayerTest() {
		Player newPlayer = new Player();
		newPlayer.setScore(10);
		newPlayer.setCurrentDecision(Decision.BETRAY);
		Game game = new Game(10);
		game.setPlayer1(newPlayer);
		
		List<Game> games = RestServer.getGames();
		int indexGameFound = -1;
		for(int i = 0; i < games.size(); i++) {
			boolean isFound = false;
	    	if(games.get(i).getPlayer1() != null && games.get(i).getPlayer2() != null)
	    	{
	    		if(games.get(i).getPlayer1().getId() == newPlayer.getId() || games.get(i).getPlayer2().getId() == newPlayer.getId()) {
	    			isFound = true;
	    		}
	    	}else if(games.get(i).getPlayer2() != null){
	    		if(games.get(i).getPlayer2().getId() == newPlayer.getId()) {
	    			isFound = true;
	    		}
	    	}else if(games.get(i).getPlayer1() != null) {
	    		if(games.get(i).getPlayer1().getId() == newPlayer.getId()) {
	    			isFound = true;
	    		}
	    	}
	    	if(isFound) {
	    		indexGameFound = i;
	    	}
		}
		ResponseEntity<Player> updatePlayer = controller.updatePlayer(games.get(indexGameFound).getId(), newPlayer.getId(), newPlayer);
		assertEquals(newPlayer.getScore(), updatePlayer.getBody().getScore());
		assertEquals(newPlayer.getCurrentDecision(), updatePlayer.getBody().getCurrentDecision());
	}

}
