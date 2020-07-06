package com.yang.nbaplayerapi.wrapper;

import com.yang.nbaplayerapi.model.PlayerInfo;
import lombok.Data;

import java.util.Set;

@Data
public class PlayerCollection {

    private Set<PlayerInfo> playerInfo;
}
