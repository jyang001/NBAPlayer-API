package com.yang.nbaplayerapi.comparator;

import com.yang.nbaplayerapi.model.PlayerInfo;

import java.util.Comparator;

public class LastNameComparator implements Comparator<PlayerInfo> {

    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        if (p1.getLastName() == p2.getLastName()) return p1.getFirstName().compareTo(p2.getFirstName());
        else {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    }

}
