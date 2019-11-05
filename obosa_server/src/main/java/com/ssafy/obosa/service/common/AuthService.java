package com.ssafy.obosa.service.common;

import com.ssafy.obosa.enumeration.LoginState;
import com.ssafy.obosa.model.common.LoginReq;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.util.SHA256Util;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private final UserRepository userRepository;


    public AuthService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public LoginState loginCheck(LoginReq loginReq)
    {
        final User user = userRepository.findUserByEmail(loginReq.getEmail());
        //탈퇴된 계정일 시, login 불가
        if(user.isWithDraw()){
            return LoginState.BANNED;
        }
        SHA256Util sha256Util = new SHA256Util();
        if(user != null)
        {
            String hashedPw = sha256Util.SHA256Util(loginReq.getPassword()+user.getSalt());
            if(hashedPw.equals(user.getPassword()))
            {
                return LoginState.SUCCESS;
            }
        }
        return LoginState.FAIL;
    }

    public boolean emailCheck(String email)
    {
        if(userRepository.findByEmail(email).isPresent())
        {
            return true;
        }
        return false;
    }
}
