package com.yang.nbaplayerapi.service;

import com.yang.nbaplayerapi.comparator.FirstNameComparator;
import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    RestTemplate restTemplate;

    public PlayerCollection getPlayers() {
        return restTemplate.getForObject("http://data.nba.net/prod/v1/2019/players.json", PlayerCollection.class);
    }

    public ResponseEntity<PlayerCollection> getPlayers(String sortBy, String direction) {

        PlayerCollection playerCollection = getPlayers();

        if (sortBy == null) {
            return ResponseEntity.ok().body(playerCollection);
        }

        List<PlayerInfo> playerList = playerCollection.getPlayerInfoList();

        switch(sortBy) {
            case "firstname":
                playerList.sort(new FirstNameComparator());
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if(direction != null) {
            playerList = orderPlayers(playerList, direction);
        }

        playerCollection.setPlayerInfoList(playerList);
        return ResponseEntity.ok().body(playerCollection);
    }


    private List<PlayerInfo> orderPlayers(List<PlayerInfo> playerInfoList, String direction) {
        if (direction.equals("asc")) {
            Collections.reverse(playerInfoList);
        }
        return playerInfoList;
    }

}
