package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.SignupFormDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.S3Util;
import com.ssafy.obosa.util.SHA256Util;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SignUpService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FileService fileService;

//    public SignUpService(final UserRepository userRepository, final FileService fileService)
//    {
//        this.userRepository = userRepository;
//        this.fileService = fileService;
//    }

    @Value("${uploadpath.user}")
    private String baseDir;

    @Value("${PASSWORD.KEY}")
    private String pwdKey;

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
