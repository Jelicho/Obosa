package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.JwtService;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(final UserRepository userRepository, final JwtService jwtService)
    {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public DefaultRes<List<User>>  readuserList()
    {
        final List<User> userList = userRepository.findAll();

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userList);
    }
    public User getUserByJwtToken(String jwtToken)
    {
        String email = jwtService.decode(jwtToken);

        if(email == null) return null;

        if(userRepository.findByEmail(email).isPresent())
        {
            return userRepository.findByEmail(email).get();
        }
        return null;
    }

}
