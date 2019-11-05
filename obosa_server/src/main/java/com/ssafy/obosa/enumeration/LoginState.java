package com.ssafy.obosa.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginState {
    SUCCESS(1),
    FAIL(2),
    BANNED(3);
    private int state;
}
