package com.ssafy.obosa.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JwtToken
{
    private Token accessToken;
    private Token refreshToken;
}
