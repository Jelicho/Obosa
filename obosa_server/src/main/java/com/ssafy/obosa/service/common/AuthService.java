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

    private final SHA256Util sha256Util;

    public AuthService(final UserRepository userRepository, final SHA256Util sha256Util)
    {
        this.userRepository = userRepository;
        this.sha256Util = sha256Util;
    }

    public boolean loginCheck(LoginReq loginReq)
    {
        final User user = userRepository.findUserByEmail(loginReq.getEmail());
        if(user != null)
        {
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
