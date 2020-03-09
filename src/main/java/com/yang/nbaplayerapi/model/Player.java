package com.yang.nbaplayerapi.model;

import lombok.Data;

public @Data class Player {

    String firstName;
    String lastName;
    int personId;
    String yearsPro;
    String heightFeet;
    String heightInches;
    /** jersey number **/
    int jersey;
}
