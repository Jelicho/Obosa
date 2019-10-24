package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.common.LoginReq;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.service.MyinfoService;
import com.ssafy.obosa.service.UserService;
import com.ssafy.obosa.service.common.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("auth")
@Controller
public class AuthController
{
    private final JwtService jwtService;

    private final MyinfoService myinfoService;

    private final UserService userService;

    public AuthController(JwtService jwtService, MyinfoService myinfoService, UserService userService)
    {
        this.jwtService = jwtService;
        this.myinfoService = myinfoService;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody final LoginReq loginReq)
    {
        return new ResponseEntity(jwtService.generateToken(loginReq), HttpStatus.OK);
    }

    @PostMapping("refresh")
    public ResponseEntity refreshToken(@RequestHeader("Authorization") String refreshToken)
    {
        return new ResponseEntity(jwtService.renewAccessToken(refreshToken),HttpStatus.OK);
    }

    @PostMapping("verification")
    public ResponseEntity verifyPassword(@RequestHeader(value = "Authorization", required = false) String jwtToken,
                                         @RequestBody final String password)
    {
        try
        {
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(myinfoService.verifyPassword(user,password), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
