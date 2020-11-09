package com.yang.nbaplayerapi.service;

import com.yang.nbaplayerapi.comparator.*;
import com.yang.nbaplayerapi.exception.PlayerNotFoundException;
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

    public PlayerCollection getPlayers(int year) {
        return restTemplate.getForObject("http://data.nba.net/prod/v1/" + year + "/players.json", PlayerCollection.class);
    }

    public PlayerCollection getPlayers(int year, String sortBy, String direction) {

        PlayerCollection playerCollection = getPlayers(year);
        if (sortBy == null) {
            return playerCollection;
        }

        List<PlayerInfo> playerList = playerCollection.getPlayerInfoList();

        switch(sortBy) {
            case "firstname":
                playerList.sort(new FirstNameComparator());
                break;
            case "height":
                playerList.sort(new HeightComparator());
                break;
            case "lastname":
                playerList.sort(new LastNameComparator());
                break;
            case "playerid":
                playerList.sort(new PlayerIdComparator());
                break;
            case "yearspro":
                playerList.sort(new YearsProComparator());
                break;
            default:
                throw new PlayerNotFoundException("sortBy parameter is invalid");
        }

        //manages 'direction' parameter
        if(direction != null && !direction.equals("asc") && !direction.equals("desc")) {
            throw new PlayerNotFoundException("direction parameter is invalid");
        }
        else if (direction != null) {
            orderPlayers(playerList, direction);
        }

        playerCollection.setPlayerInfoList(playerList);
        return playerCollection;
    }

    private void orderPlayers(List<PlayerInfo> playerInfoList, String direction) {
        if (direction.equals("asc")) {
            Collections.reverse(playerInfoList);
        }
    }

    public ResponseEntity<PlayerInfo> getPlayer(int year, String firstName, String lastName) {
        PlayerCollection playerCollection = getPlayers(year);
        assert playerCollection != null;
        for (PlayerInfo playerInfo : playerCollection.getPlayerInfoList()){
            String playerFirstName = playerInfo.getFirstName().toLowerCase();
            String playerLastName = playerInfo.getLastName().toLowerCase();
            if(playerFirstName.equals(firstName) && playerLastName.equals(lastName)) {
                return ResponseEntity.ok().body(playerInfo);
            }
            throw new PlayerNotFoundException("Player with the given firstName and lastName not found");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



}
