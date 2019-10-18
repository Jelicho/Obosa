package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.validation.constraints.NotNull;

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
    @NonNull
    private int uid;

    @NonNull
    private String password;

    @NonNull
    private int salt;

    @NonNull
    private String email;

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

    private Boolean State;
}
