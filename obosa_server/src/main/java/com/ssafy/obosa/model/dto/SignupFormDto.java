package com.ssafy.obosa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupFormDto
{
    private String email;
    private String password;
    private int salt;
    private String name;
    private String nickname;
    private String address;
    private String zipCode;
    private String phone;
    private MultipartFile profileImgFile;
    private String profileImg;
    private boolean State;

    public SignupFormDto(String email, String password, String name, String nickname, String address, String zipCode, String phone, String profileImg) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.address = address;
        this.zipCode = zipCode;
        this.phone = phone;
        this.profileImg = profileImg;
    }
}
