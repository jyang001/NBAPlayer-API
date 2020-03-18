package com.yang.nbaplayerapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.nbaplayerapi.model.Player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public final class PlayerUtil {

    /**
     * base url to query
     */
    private static final String startUrl = "http://data.nba.net/prod/v1/";

    private PlayerUtil() {

    }

    public static List<Player> getPlayers(String inputUrl) {

        Set<Player> myPlayers = new HashSet<>();
        List<Player> players = new ArrayList<>();

        String parseUrl = startUrl+inputUrl;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            URL url = new URL(parseUrl);
            JsonNode resultsNode = objectMapper.readTree(url);
            JsonNode leagueNode = resultsNode.path("league");
            JsonNode standardNode = leagueNode.path("standard");
            Iterator<JsonNode> iterator = standardNode.elements();
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                Player player = objectMapper.treeToValue(node, Player.class);
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return players;
    }


}
