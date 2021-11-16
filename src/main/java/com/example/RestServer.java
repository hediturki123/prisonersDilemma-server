package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import object.Game;

@SpringBootApplication
public class RestServer {
	
	public static List<Game> games = new ArrayList<>();
	
    public static void main(String[] args) {
        SpringApplication.run(RestServer.class, args);
    }

}
