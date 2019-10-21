package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.SignupFormDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SignUpService
{
    private final UserRepository userRepository;

    private final FileService fileService;

    public SignUpService(final UserRepository userRepository, final FileService fileService)
    {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    @Value("${uploadpath.user}")
    private String baseDir;

//    @Value("${PASSWORD.KEY}")
//    private String pwdKey;

    @Value("${AES.SECRET}")
    private String aesKey;

    public DefaultRes<SignupFormDto> newUser(SignupFormDto signupFormDto, MultipartFile profileImgFile)
    {
        try
        {
            String email = signupFormDto.getEmail();
            if(userRepository.findByEmail(email).isPresent())
            {
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.UNABLE_USER);
            }

            User user = User.setUserBySignupDto(signupFormDto);
            String pw = user.getPassword();
            int salt = user.getSalt();

            SHA256Util sha256Util = new SHA256Util();

            String newPw = sha256Util.SHA256Util(pw+salt);
            user.setPassword(newPw);

            AES256Util aes256Util = new AES256Util(aesKey);

            String name = aes256Util.aesEncoding(user.getName());
            String phone = aes256Util.aesEncoding(user.getPhone());
            String zipCode = aes256Util.aesEncoding(user.getZipCode());
            String address = aes256Util.aesEncoding(user.getAddress());

            user.setName(name);
            user.setPhone(phone);
            user.setZipCode(zipCode);
            user.setAddress(address);

            if(profileImgFile != null)
            {
                String filePath = new StringBuilder(baseDir)
                                    .append(S3Util.getUuid())
                                    .append(profileImgFile.getOriginalFilename()).toString();

                fileService.fileUpload(profileImgFile, filePath);
                user.setProfileImg(filePath);
            }
            else
            {
                user.setProfileImg("파일 없음");
                //디폴트 이미지 넣는 로직 간단하게
            }

            userRepository.save(user);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    public DefaultRes duplicateEmail(String email)
    {
        if(userRepository.findByEmail(email).isPresent())
        {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.UNABLE_EMAIL, true);
        }
        else
        {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.ABLE_EMAIL, false);
        }
    }
}
