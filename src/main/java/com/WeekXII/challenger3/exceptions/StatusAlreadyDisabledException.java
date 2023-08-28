package com.WeekXII.challenger3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusAlreadyDisabledException extends RuntimeException{

    public StatusAlreadyDisabledException() {
        super(String.format("Status already is DISABLED"));
    }
}
