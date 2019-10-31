package com.ssafy.obosa.exception;

public class LowerThanCurrentBidPriceException extends RuntimeException {

    public LowerThanCurrentBidPriceException(String message) {
        super(message);
    }
}
