package com.yang.nbaplayerapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class PlayerInfo {

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("lastName")
    String lastName;

    @JsonProperty("personId")
    String personId;

    /** jersey number **/
    @JsonProperty("jersey")
    String jersey;

    @JsonProperty("pos")
    String position;

    @JsonProperty("heightFeet")
    String heightFeet;

    @JsonProperty("heightInches")
    String heightInches;

    @JsonProperty("yearsPro")
    String yearsPro;
}
