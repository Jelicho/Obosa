package com.ssafy.obosa.service;

import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.MyinfoChangeDto;
import com.ssafy.obosa.model.dto.MyinfoDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MyinfoService {

    private final UserRepository userRepository;

    private final FileService fileService;

    @Value("${uploadpath.user}")
    private String baseDir;

    @Value("${cloud.aws.endpoint}")
    private String s3Endpoint;

    @Value("${AES.SECRET}")
    private String aesKey;

    public MyinfoService(UserRepository userRepository, FileService fileService)
    {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    @Transactional(readOnly = true)
    public DefaultRes<MyinfoDto> readMypage(User user)
    {
        try
        {
            MyinfoDto myinfoDto = user.getMyinfoDto();
            AES256Util aes256Util = new AES256Util(aesKey);

            myinfoDto.builder()
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .name(aes256Util.aesDecoding(myinfoDto.getName()))
                    .phone(aes256Util.aesDecoding(myinfoDto.getPhone()))
                    .zipCode(aes256Util.aesDecoding(myinfoDto.getZipCode()))
                    .address(aes256Util.aesDecoding(myinfoDto.getAddress()))
                    .profileImg(S3Util.getImgPath(s3Endpoint, user.getProfileImg()))
                    .build();

            myinfoDto.setProfileImg(S3Util.getImgPath(s3Endpoint, user.getProfileImg()));

            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_MYINFO, myinfoDto);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    public DefaultRes updateUserInfo(User user, MyinfoChangeDto myinfoChangeDto, MultipartFile profileImg)
    {
        try
        {
            if(profileImg != null)
           {
               fileService.fileDelete(baseDir+user.getProfileImg());
               String filePath = S3Util.getFilePath(baseDir, profileImg);
               fileService.fileUpload(profileImg, filePath);
               user.setProfileImg(filePath);
           }
           else
           {
                //todo Default image 넣는 로직
           }

            SHA256Util sha256Util = new SHA256Util();
           AES256Util aes256Util = new AES256Util(aesKey);
           if(myinfoChangeDto.getPassword() != null)
           {
               String newPassword = sha256Util.SHA256Util(myinfoChangeDto.getPassword()+user.getSalt());
               user.setPassword(newPassword);
           }

           user.setPhone(aes256Util.aesEncoding(myinfoChangeDto.getPhone()));
           user.setZipCode(aes256Util.aesEncoding(myinfoChangeDto.getZipCode()));
           user.setAddress(aes256Util.aesEncoding(myinfoChangeDto.getAddress()));

           userRepository.save(user);

           return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public DefaultRes verifyPassword(User user, String password)
    {
        try
        {
            SHA256Util sha256Util = new SHA256Util();
            if(password == null)
            {
                return DefaultRes.res(StatusCode.UNAUTHORIZED, ResponseMessage.AUTH_FAIL);
            }
            else
            {
                String hashedPassword = sha256Util.SHA256Util(password+user.getSalt());
                if(hashedPassword.equals(user.getPassword()))
                {
                    return DefaultRes.res(StatusCode.OK, ResponseMessage.AUTH_SUCCESS);
                }
                else
                {
                    return DefaultRes.res(StatusCode.UNAUTHORIZED, ResponseMessage.AUTH_FAIL);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
}
