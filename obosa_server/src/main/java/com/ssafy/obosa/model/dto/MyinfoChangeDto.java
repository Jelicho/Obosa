package com.ssafy.obosa.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyinfoChangeDto
{
    private String password;
    private String phone;
    private String profileImg;
    private String zipCode;
    private String address;
}
