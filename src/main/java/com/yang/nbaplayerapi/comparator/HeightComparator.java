package com.yang.nbaplayerapi.comparator;

import com.yang.nbaplayerapi.model.PlayerInfo;

import java.util.Comparator;

public class HeightComparator implements Comparator<PlayerInfo> {

    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        int p1Height = Integer.parseInt(p1.getHeightCentimeters());
        int p2Height = Integer.parseInt(p2.getHeightCentimeters());
        return p1Height-p2Height;
    }

}
