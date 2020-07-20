package com.yang.nbaplayerapi.comparator;

import com.yang.nbaplayerapi.model.PlayerInfo;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<PlayerInfo> {

    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        if (p1.getFirstName().equals(p2.getFirstName())) return p1.getLastName().compareTo(p2.getLastName());
        else {
            return p1.getFirstName().compareTo(p2.getFirstName());
        }
    }

}
