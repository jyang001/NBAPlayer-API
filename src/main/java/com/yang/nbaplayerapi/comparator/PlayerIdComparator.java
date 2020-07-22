package com.yang.nbaplayerapi.comparator;

import com.yang.nbaplayerapi.model.PlayerInfo;

import java.util.Comparator;

public class PlayerIdComparator implements Comparator<PlayerInfo> {

    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        int player1_id = Integer.parseInt(p1.getPersonId());
        int player2_id = Integer.parseInt(p2.getPersonId());
        return player1_id-player2_id;
    }

}
