package com.sincrohandover.api.shared.domain.exception;

public class OutsideWorkingHoursException extends RuntimeException{
    public OutsideWorkingHoursException(String message){
        super(message);
    }
}
