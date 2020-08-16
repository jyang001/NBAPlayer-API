package com.yang.nbaplayerapi.controller;

import com.yang.nbaplayerapi.service.PlayerService;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/api/ping")
    public ResponseEntity<String> pingTest() {
        return new ResponseEntity<>("success: true", HttpStatus.OK);
    }

    @GetMapping(path="/api/players")
    public ResponseEntity<PlayerCollection> getAllPlayers(@RequestParam(value="sortBy", required = false) String sortBy) {
        return playerService.getPlayers(sortBy);
    }
}
