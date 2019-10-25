package com.ssafy.obosa.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.obosa.model.domain.auditing.DateEntity;
import com.ssafy.obosa.model.dto.MyinfoDto;
import com.ssafy.obosa.model.dto.SignupFormDto;
import com.ssafy.obosa.util.AES256Util;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateEntity
{
    @Id
    @NonNull
    private int uid;

    @NonNull
    private String email;
    
    @NonNull
    private String password;

    @NonNull
    private int salt;

    @NonNull
    private String name;

    @NonNull
    private String nickname;

    @NonNull
    private String address;

    @NonNull
    private String zipCode;

    @NonNull
    private String phone;

    private String profileImg;

    private boolean withDraw = false;

    private boolean state;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Product> userProductList = new ArrayList<>();

    public String getDecodedName()
    {
        try
        {
            AES256Util aes256Util = new AES256Util("j5nt51SDgt35sdgklnG45AD3Adsg5nDG");
            return aes256Util.aesDecoding(this.name);
        }
        catch (Exception e)
        {
            return "DECODE_ERROR";
        }
    }

    public String getDecodedPhone()
    {
        try
        {
            AES256Util aes256Util = new AES256Util("j5nt51SDgt35sdgklnG45AD3Adsg5nDG");
            return aes256Util.aesDecoding(this.phone);
        }
        catch (Exception e)
        {
            return "DECODE_ERROR";
        }
    }

    public String getDecodedZipCode()
    {
        try
        {
            AES256Util aes256Util = new AES256Util("j5nt51SDgt35sdgklnG45AD3Adsg5nDG");
            return aes256Util.aesDecoding(this.zipCode);
        }
        catch (Exception e)
        {
            return "DECODE_ERROR";
        }
    }

    public  String getDecodedAddress()
    {
        try
        {
            AES256Util aes256Util = new AES256Util("j5nt51SDgt35sdgklnG45AD3Adsg5nDG");
            return aes256Util.aesDecoding(this.address);
        }
        catch (Exception e)
        {
            return "DECODE_ERROR";
        }
    }

    public MyinfoDto getMyinfoDto()
    {
        MyinfoDto myinfoDto = MyinfoDto.builder()
                .email(this.email)
                .profileImg(this.profileImg)
                .name(this.name)
                .nickname(this.nickname)
                .phone(this.phone)
                .zipCode(this.zipCode)
                .address(this.address)
                .build();

        return myinfoDto;
    }

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
