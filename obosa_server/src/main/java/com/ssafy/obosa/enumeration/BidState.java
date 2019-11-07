package com.ssafy.obosa.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidState {
    PAY_COMPLETE(1),
    SHIPPING(2),
    SHIP_RECEIVED(3),
    NOT_PAY(4);
    private int state;
}
