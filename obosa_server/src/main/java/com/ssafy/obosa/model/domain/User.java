package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private String password;
    private int salt;
    private String email;
    private String address;
    private String zipCode;
    private String phone;
    private String profileImg;
    private Boolean State;
    private String registeredDate;
    private String modifiedDate;
}
