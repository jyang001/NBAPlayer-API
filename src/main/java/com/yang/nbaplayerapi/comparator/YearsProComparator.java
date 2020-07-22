package com.yang.nbaplayerapi.comparator;

import com.yang.nbaplayerapi.model.PlayerInfo;

import java.util.Comparator;

public class YearsProComparator implements Comparator<PlayerInfo> {

    @Override
    public int compare(PlayerInfo p1, PlayerInfo p2) {
        int p1_years = Integer.parseInt(p1.getYearsPro());
        int p2_years = Integer.parseInt(p2.getYearsPro());
        return p1_years-p2_years;
    }

}
