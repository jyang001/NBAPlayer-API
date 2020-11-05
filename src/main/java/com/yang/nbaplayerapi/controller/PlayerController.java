package com.yang.nbaplayerapi.controller;

import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.service.PlayerService;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    PlayerService playerService;

    @GetMapping("/api/ping")
    public ResponseEntity<String> pingTest() {
        return new ResponseEntity<>("success: true", HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<PlayerCollection> getAllPlayers(
            @RequestParam(value="year") int year,
            @RequestParam(value="sortBy", required = false) String sortBy,
            @RequestParam(value="direction", required = false) String direction)
    {
        PlayerCollection playerCollection = playerService.getPlayers(year, sortBy, direction);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(playerCollection, headers, HttpStatus.OK);
    }

    @GetMapping("/player")
    public ResponseEntity<PlayerInfo> getOnePlayer(
            @RequestParam(value="year") int year,
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName)
    {
        return playerService.getPlayer(year,firstName,lastName);
    }
    
}
