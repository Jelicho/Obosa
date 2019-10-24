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
    private MultipartFile profileImg;
    private boolean State;
}
