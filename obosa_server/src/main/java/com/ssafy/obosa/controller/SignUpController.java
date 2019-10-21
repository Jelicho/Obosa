package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.dto.SignupFormDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("signup")
public class SignUpController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    SignUpService signUpService;

    @GetMapping("duplicateEmail")
    public ResponseEntity duplicateUserEmail(@RequestParam String email)
    {
        return new ResponseEntity(signUpService.duplicateEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity signup(SignupFormDto signupFormDto, @RequestPart(value="profileImgFile", required = false) final MultipartFile profileImgFile)
    {
        return new ResponseEntity(signUpService.newUser(signupFormDto, profileImgFile), HttpStatus.OK);
    }
}
