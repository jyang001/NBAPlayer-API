package com.yang.nbaplayerapi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

public class PlayerCollectionJsonDeserializer extends JsonDeserializer<PlayerCollection> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public PlayerCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        PlayerCollection playerCollection = new PlayerCollection();

        setUpObjectMapper();

        JsonNode resultsNode = jsonParser.readValueAsTree();
        JsonNode leagueNode = resultsNode.path("league");
        JsonNode standardNode = leagueNode.path("standard");

        String stringValuesOfJson = standardNode.toString();
        List<PlayerInfo> playerInfos = objectMapper.readValue(stringValuesOfJson, new TypeReference<List<PlayerInfo>>(){});

        System.out.println("Jackson deserializer playerinfo1 \n"+
                playerInfos);

        playerCollection.setPlayerInfoList(playerInfos);


        return playerCollection;
    }

    private void setUpObjectMapper() throws Exception {
        objectMapper.disable(MapperFeature.AUTO_DETECT_CREATORS,
                MapperFeature.AUTO_DETECT_FIELDS,
                MapperFeature.AUTO_DETECT_GETTERS,
                MapperFeature.AUTO_DETECT_IS_GETTERS);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

//    private void removeInactivePlayers(List<PlayerInfo> playerInfoList) {
//        playerInfoList.removeIf(playerInfo -> playerInfo.getYearsPro().equals(""));
//    }

}
