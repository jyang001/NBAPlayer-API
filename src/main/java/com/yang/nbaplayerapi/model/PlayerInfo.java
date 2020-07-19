package com.yang.nbaplayerapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

    @JsonProperty("heightMeters")
    @Setter(AccessLevel.NONE)
    String heightMeters;

    String heightCentimeters;

    @JsonProperty("heightInches")
    String heightInches;

    @JsonProperty("yearsPro")
    String yearsPro;

    public void setHeightMeters(String heightMeters) {
        if (heightMeters.equals("")) {
            this.heightMeters = "-";
            this.heightCentimeters = "-";
        }
        else {
            this.heightMeters = heightMeters;
            double tempHeightMeters = Double.parseDouble(heightMeters);
            setHeightCentimeters(Double.toString(tempHeightMeters * 100));
        }
    }

}
