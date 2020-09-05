package com.yang.nbaplayerapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorMessage {

    private String errorMessage;
    private String requestingURI;
    private Instant timeStamp;

}
