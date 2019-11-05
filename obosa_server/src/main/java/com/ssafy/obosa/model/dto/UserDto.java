package com.ssafy.obosa.model.dto;

import com.ssafy.obosa.model.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserDto {
    int uid;
    String nickname;
    String profileImg;
    public static UserDto setUserDtoByUser(User user)
    {
        return UserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .build();
    }
}
