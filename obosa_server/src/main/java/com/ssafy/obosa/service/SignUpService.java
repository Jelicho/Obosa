package com.ssafy.obosa.service;

import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.SignupFormDto;
import com.ssafy.obosa.registration.OnRegistrationCompleteEvent;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.repository.VerificationTokenRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class  SignUpService
{
    private final UserService userService;

    private final UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;

    private final FileService fileService;

    private final ApplicationEventPublisher eventPublisher;

    public SignUpService(final UserService userService, final UserRepository userRepository, VerificationTokenRepository tokenRepository, final FileService fileService, final ApplicationEventPublisher eventPublisher)
    {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.fileService = fileService;
        this.eventPublisher = eventPublisher;
    }

    @Value("${uploadpath.user}")
    private String baseDir;

    @Value("${AES.SECRET}")
    private String aesKey;

    public DefaultRes<SignupFormDto> newUser(SignupFormDto signupFormDto)
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
            MultipartFile profileImgFile = signupFormDto.getProfileImgFile();
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
                user.setProfileImg("obosa/user/default_user_img.png");
                //디폴트 이미지 넣는 로직 간단하게
            }

            userRepository.save(user);

            /*
                publish registrationComplete event
                TODO: bind appUrl with the context path of request
             */
//            String appUrl = request.getContextPath();
            String appUrl = "http://obosa.saffy.io";
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (userRepository.findUserByEmail(user.getEmail()), null, appUrl));

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public DefaultRes confirmEmail(String token) {
        try {
            ResponseMessage responseMessage = userService.validateVerificationToken(token);
            return DefaultRes.res(StatusCode.OK, responseMessage, true);
        } catch (Exception e) {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public DefaultRes duplicateNickname(String nickname) {
        if(userRepository.findUserByNickname(nickname)==null)
        {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.ABLE_NICKNAME, true);
        }
        else
        {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.UNABLE_NICKNAME, false);
        }
    }
}
