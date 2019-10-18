package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import com.ssafy.obosa.model.dto.SignupFormDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateEntity
{
    @Id
    private int uid;
    private String email;
    private String password;
    private int salt;
    private String name;
    private String nickname;
    private String address;
    private String zipCode;
    private String phone;
    private String profileImg;
    private boolean state;

    public static User setUserBySignupDto(SignupFormDto signupFormDto)
    {
        int salt = (int)(Math.random() * 100);
        return User.builder()
                .email(signupFormDto.getEmail())
                .password(signupFormDto.getPassword())
                .salt(salt)
                .name(signupFormDto.getName())
                .nickname(signupFormDto.getNickname())
                .address(signupFormDto.getAddress())
                .zipCode(signupFormDto.getZipCode())
                .phone(signupFormDto.getPhone())
                .state(signupFormDto.isState())
                .build();
    }
}
