package com.ssafy.obosa.service.common;

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

    public boolean loginCheck(LoginReq loginReq)
    {
        final User user = userRepository.findUserByEmail(loginReq.getEmail());
        if(user != null)
        {
            SHA256Util sha256Util = new SHA256Util();

            String hashedPw = sha256Util.SHA256Util(loginReq.getPassword()+user.getSalt());
            if(hashedPw.equals(user.getPassword()))
            {
                return true;
            }
            return false;
        }
        return false;
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
