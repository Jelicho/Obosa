package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.MyinfoDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.util.AES256Util;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.S3Util;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MyinfoService {
    private final UserRepository userRepository;

    @Value("${cloud.aws.endpoint}")
    private String s3Endpoint;

    @Value("${AES.SECRET}")
    private String aesKey;

    public MyinfoService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public DefaultRes<MyinfoDto> readMypage(User user)
    {
        try
        {
            AES256Util aes256Util = new AES256Util(aesKey);

            MyinfoDto myinfoDto = user.getMyinfoDto();
            myinfoDto.setName(aes256Util.aesDecoding(myinfoDto.getName()));
            myinfoDto.setPhone(aes256Util.aesDecoding(myinfoDto.getPhone()));
            myinfoDto.setZipCode(aes256Util.aesDecoding(myinfoDto.getZipCode()));
            myinfoDto.setAddress(aes256Util.aesDecoding(myinfoDto.getAddress()));

            myinfoDto.setProfileImg(S3Util.getImgPath(s3Endpoint, user.getProfileImg()));

            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_MYINFO, myinfoDto);
        }
        catch (Exception e)
        {
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
}
