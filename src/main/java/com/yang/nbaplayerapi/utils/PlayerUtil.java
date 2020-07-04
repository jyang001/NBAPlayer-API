package com.yang.nbaplayerapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.nbaplayerapi.model.PlayerInfo;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public final class PlayerUtil {

    /**
     * base url to query
     */
    private static final String startUrl = "http://data.nba.net/prod/v1/";

    private PlayerUtil() {

    }

    public static List<PlayerInfo> getPlayers(String inputUrl) {

        Set<PlayerInfo> myPlayerInfos = new HashSet<>();
        List<PlayerInfo> playerInfos = new ArrayList<>();

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
                PlayerInfo playerInfo = objectMapper.treeToValue(node, PlayerInfo.class);
                playerInfos.add(playerInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playerInfos;
    }


}
