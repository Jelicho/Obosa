package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.DeleteWinningBidDto;
import com.ssafy.obosa.model.dto.ReadWinningBidDto;
import com.ssafy.obosa.model.dto.UpdateWinningBidDto;
import com.ssafy.obosa.service.DeleteWinningBidService;
import com.ssafy.obosa.service.ReadWinningBidService;
import com.ssafy.obosa.service.UpdateWinningBidService;
import com.ssafy.obosa.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/winningbid")
public class WinningBidController {
    private final DeleteWinningBidService deleteWinningBidService;
    private final UpdateWinningBidService updateWinningBidService;
    private final ReadWinningBidService readWinningBidService;
    private final UserService userService;
    WinningBidController(final DeleteWinningBidService deleteWinningBidService, final UpdateWinningBidService updateWinningBidService, final ReadWinningBidService readWinningBidService
                        ,final UserService userService){
        this.deleteWinningBidService = deleteWinningBidService;
        this.updateWinningBidService = updateWinningBidService;
        this.readWinningBidService = readWinningBidService;
        this.userService = userService;
    }
    @GetMapping("/{wid}")
    public ResponseEntity getWinningBid(@RequestHeader(value = "Authorization", required = false) String jwtToken, @PathVariable("wid") int wid){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            ReadWinningBidDto readWinningBidDto = new ReadWinningBidDto(wid);
            return new ResponseEntity(readWinningBidService.readWinningBid(user, readWinningBidDto), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/seller")
    public ResponseEntity getWinningBidsForSeller(@RequestHeader(value = "Authorization", required = false) String jwtToken,  Pageable pageable){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            int uid = user.getUid();
            ReadWinningBidDto readWinningBidDto = new ReadWinningBidDto(uid);
            return new ResponseEntity(readWinningBidService.readWinningBidsForSeller(readWinningBidDto, pageable), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/winner")
    public ResponseEntity getWinningBidsForWinner(@RequestHeader(value = "Authorization", required = false) String jwtToken, Pageable pageable){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            int uid = user.getUid();
            ReadWinningBidDto readWinningBidDto = new ReadWinningBidDto(uid);
            return new ResponseEntity(readWinningBidService.readWinningBidsForWinner(readWinningBidDto, pageable), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    //(판매자가) 계좌번호를 보낸다.
//    @PutMapping("/payback")
//    public ResponseEntity updateWinningBid(UpdateWinningBidDto updateWinningBidDto){
//        return new ResponseEntity(updateWinningBidService.updateWinningBidState(updateWinningBidDto), HttpStatus.OK);
//    }

    //낙찰을 받았으나, 결제를 하지 않음
    @PutMapping("/outstanding")
    public ResponseEntity updateBidStateToOutstanding(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateWinningBidDto updateWinningBidDto){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            int bidState = 4;
            return new ResponseEntity(updateWinningBidService.updateWinningBidState(user, updateWinningBidDto, bidState), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //수령확인
    @PutMapping("/receipt")
    public ResponseEntity updateBidStateToReceipt(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateWinningBidDto updateWinningBidDto){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            int bidState = 3;
            return new ResponseEntity(updateWinningBidService.updateWinningBidState(user, updateWinningBidDto, bidState), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //배송시작
    @PutMapping("/shipment")
    public ResponseEntity updateBidStateToShipment(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateWinningBidDto updateWinningBidDto){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            int bidState = 2;
            return new ResponseEntity(updateWinningBidService.updateWinningBidState(user, updateWinningBidDto, bidState), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //결제완료
    @PutMapping("/pay")
    public ResponseEntity updateBidStateToPay(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateWinningBidDto updateWinningBidDto){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            int bidState = 1;
            return new ResponseEntity(updateWinningBidService.updateWinningBidState(user, updateWinningBidDto, bidState), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //주소수정
    @PutMapping
    public ResponseEntity updateAddress(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateWinningBidDto updateWinningBidDto){
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(updateWinningBidService.updateAddress(user, updateWinningBidDto), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
        }

    }

    //수령확인 후 완벽 종료
    @DeleteMapping
    public ResponseEntity deleteWinningBidAndAuction(@RequestHeader(value = "Authorization", required = false) String jwtToken, DeleteWinningBidDto deleteWinningBidDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(deleteWinningBidService.deleteBidAndAuction(user, deleteWinningBidDto), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
        }

    }
}
