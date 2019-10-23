package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.MyinfoChangeDto;
import com.ssafy.obosa.service.MyinfoService;
import com.ssafy.obosa.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("mypage")
@Controller
public class MyinfoController
{
    private final MyinfoService myinfoService;

    private final UserService userService;

    public MyinfoController(MyinfoService myinfoService, UserService userService)
    {
        this.myinfoService = myinfoService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity readMyinfo(@RequestHeader(value = "Authorization", required = false) String jwtToken)
    {
        try
        {
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity(myinfoService.readMypage(user), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateUserInfo(@RequestHeader(value = "Authorization", required = false) String jwtToken,
                                         MyinfoChangeDto myinfoChangeDto,
                                         @RequestPart(value="profileImgFile", required = false)  MultipartFile profileImg)
    {
        return null;
    }
}
