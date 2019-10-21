package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.common.LoginReq;
import com.ssafy.obosa.service.common.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    JwtService jwtService;

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
}
