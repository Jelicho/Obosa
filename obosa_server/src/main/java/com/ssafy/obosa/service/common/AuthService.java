package com.ssafy.obosa.service.common;

import com.ssafy.obosa.model.common.LoginReq;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.repository.UserRepository;
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
        final User user = userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if(user != null)
        {
            return true;
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
