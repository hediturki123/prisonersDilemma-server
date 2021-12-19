package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import game.Game;

@SpringBootApplication

public class RestServer {
	
	private static List<Game> games = new ArrayList<>();

	public static void addGame(Game game) {
		games.add(game);
	}
	
	public static List<Game> getGames() {
		return games;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(RestServer.class, args);
    }

}
