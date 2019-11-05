package com.ssafy.obosa.model.dto;


import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
