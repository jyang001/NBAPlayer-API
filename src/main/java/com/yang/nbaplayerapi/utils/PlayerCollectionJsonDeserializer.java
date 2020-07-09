package com.yang.nbaplayerapi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.wrapper.PlayerCollection;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Set;

public class PlayerCollectionJsonDeserializer extends JsonDeserializer<PlayerCollection> {

    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public PlayerCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        PlayerCollection playerCollection = new PlayerCollection();

        setUpObjectMapper();

        JsonNode resultsNode = jsonParser.readValueAsTree();
        JsonNode leagueNode = resultsNode.path("league");
        JsonNode standardNode = leagueNode.path("standard"); //an array

        String test = standardNode.toString(); //players json as string
        Set<PlayerInfo> set = objectMapper.readValue(test, new TypeReference<Set<PlayerInfo>>(){});

        playerCollection.setPlayerInfo(set);

        return playerCollection;
    }

    public void setUpObjectMapper() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.AUTO_DETECT_CREATORS,
                MapperFeature.AUTO_DETECT_FIELDS,
                MapperFeature.AUTO_DETECT_GETTERS,
                MapperFeature.AUTO_DETECT_IS_GETTERS);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

}
