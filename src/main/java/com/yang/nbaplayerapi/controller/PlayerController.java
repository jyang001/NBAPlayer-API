package com.yang.nbaplayerapi.controller;

import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.utils.PlayerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    @GetMapping("/api/ping")
    public ResponseEntity<String> pingTest() {
        return new ResponseEntity<>("success: true", HttpStatus.OK);
    }

    @GetMapping(path="/api/players")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerInfo> getPlayers() {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        String query = "2019/players.json";
        playerInfos = PlayerUtil.getPlayers(query);
        return playerInfos;
    }

}
