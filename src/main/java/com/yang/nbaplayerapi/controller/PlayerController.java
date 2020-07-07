package com.yang.nbaplayerapi.controller;

import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.utils.PlayerUtil;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<PlayerInfo> playerInfos = new HashSet<>();
        PlayerCollection playerCollection = new PlayerCollection();

        String query = "2019/players.json";
        playerInfos = PlayerUtil.getPlayers(query);
        playerCollection.setPlayerInfo(playerInfos);

        return playerCollection;
    }

}
