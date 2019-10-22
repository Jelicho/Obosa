package com.ssafy.obosa.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MyinfoDto
{
    private String email;
    private String profileImg;
    private String name;
    private String nickname;
    private String phone;
    private String zipCode;
    private String address;
}
