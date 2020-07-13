package com.yang.nbaplayerapi.controller;

import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class PlayerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/api/ping")
    public ResponseEntity<String> pingTest() {
        return new ResponseEntity<>("success: true", HttpStatus.OK);
    }

    @GetMapping(path="/api/players")
    @ResponseStatus(HttpStatus.OK)
    public PlayerCollection getPlayers() {
        PlayerCollection playerCollection = restTemplate.getForObject("http://data.nba.net/prod/v1/2019/players.json", PlayerCollection.class);
        return playerCollection;
    }

}
