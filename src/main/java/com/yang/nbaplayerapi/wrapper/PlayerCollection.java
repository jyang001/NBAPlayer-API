package com.yang.nbaplayerapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yang.nbaplayerapi.model.PlayerInfo;
import com.yang.nbaplayerapi.utils.PlayerCollectionJsonDeserializer;
import lombok.Data;

import java.util.Set;

@Data
@JsonDeserialize( using = PlayerCollectionJsonDeserializer.class)
public class PlayerCollection {

    private Set<PlayerInfo> playerInfo;
}
