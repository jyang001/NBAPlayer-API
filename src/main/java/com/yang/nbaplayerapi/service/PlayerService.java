package com.yang.nbaplayerapi.service;

import com.yang.nbaplayerapi.comparator.FirstNameComparator;
import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    RestTemplate restTemplate;

    private PlayerCollection _playerCollection;

    public PlayerCollection getPlayers() {
        return restTemplate.getForObject("http://data.nba.net/prod/v1/2019/players.json", PlayerCollection.class);
    }

    public PlayerCollection getPlayers(String sortBy) {
        PlayerCollection playerCollection = getPlayers();
        List<PlayerInfo> playerList = playerCollection.getPlayerInfo();

        switch(sortBy) {
            case "firstname":
                Collections.sort(playerList, new FirstNameComparator());
            default:
                break;
                //return error
        }
        playerCollection.setPlayerInfo(playerList);
        return playerCollection;
    }

    public PlayerCollection getPlayers(String sortBy, String direction) {
        PlayerCollection playerCollection = getPlayers(sortBy);
        return orderPlayers(playerCollection, direction);
    }

    private PlayerCollection orderPlayers(PlayerCollection playerCollection, String direction) {
        if (direction.equals("asc")) {
            List<PlayerInfo> playerInfos = playerCollection.getPlayerInfo();
            Collections.reverse(playerInfos);
            playerCollection.setPlayerInfo(playerInfos);
        }
        return playerCollection;
    }

}
