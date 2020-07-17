package com.yang.nbaplayerapi.controller;

import com.yang.nbaplayerapi.service.PlayerService;
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
    PlayerService playerService;

    @GetMapping("/api/ping")
    public ResponseEntity<String> pingTest() {
        return new ResponseEntity<>("success: true", HttpStatus.OK);
    }

    @GetMapping(path="/api/players")
    @ResponseStatus(HttpStatus.OK)
    public PlayerCollection getPlayers() {
        return playerService.getAllPlayers();
    }

}
