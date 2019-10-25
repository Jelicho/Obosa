package com.ssafy.obosa.enumeration;

import lombok.Getter;

@Getter
public enum JwtExpireTermEnum
{
    ACCESS(900), REFRESH(3600*24*365);

    private long expireTerm;

    JwtExpireTermEnum(long expireTerm)
    {
        this.expireTerm = expireTerm;
    }
}
