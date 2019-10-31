package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.dto.DeleteWinningBidDto;
import com.ssafy.obosa.model.dto.ReadWinningBidDto;
import com.ssafy.obosa.model.dto.UpdateWinningBidDto;
import com.ssafy.obosa.service.DeleteWinningBidService;
import com.ssafy.obosa.service.ReadWinningBidService;
import com.ssafy.obosa.service.UpdateWinningBidService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("winningbid")
public class WinningBidController {
    private final DeleteWinningBidService deleteWinningBidService;
    private final UpdateWinningBidService updateWinningBidService;
    private final ReadWinningBidService readWinningBidService;
    WinningBidController(final DeleteWinningBidService deleteWinningBidService, final UpdateWinningBidService updateWinningBidService, final ReadWinningBidService readWinningBidService){
        this.deleteWinningBidService = deleteWinningBidService;
        this.updateWinningBidService = updateWinningBidService;
        this.readWinningBidService = readWinningBidService;
    }
    @GetMapping("/{wid}")
    public ResponseEntity getWinningBid(@PathVariable("wid") int wid){
        ReadWinningBidDto readWinningBidDto = new ReadWinningBidDto(wid);
        return new ResponseEntity(readWinningBidService.readWinningBid(readWinningBidDto), HttpStatus.OK);
    }

    @GetMapping("/seller")
    public ResponseEntity getWinningBidsForSeller(@RequestParam("uid") int uid, Pageable pageable){
        ReadWinningBidDto readWinningBidDto = new ReadWinningBidDto(uid);
        return new ResponseEntity(readWinningBidService.readWinningBidsForSeller(readWinningBidDto, pageable), HttpStatus.OK);
    }

    @GetMapping("/winner")
    public ResponseEntity getWinningBidsForWinner(@RequestParam("uid") int uid, Pageable pageable){
        ReadWinningBidDto readWinningBidDto = new ReadWinningBidDto(uid);
        return new ResponseEntity(readWinningBidService.readWinningBidsForWinner(readWinningBidDto, pageable), HttpStatus.OK);
    }
//    //(판매자가) 계좌번호를 보낸다.
//    @PutMapping("/payback")
//    public ResponseEntity updateWinningBid(@RequestParam("wid") int wid){
//        UpdateWinningBidDto updateWinningBidDto = new UpdateWinningBidDto(wid);
//        return new ResponseEntity(updateWinningBidService.updateWinningBidState(updateWinningBidDto), HttpStatus.OK);
//    }

    //낙찰을 받았으나, 결제를 하지 않음
    @PutMapping("/outstanding")
    public ResponseEntity updateBidStateToOutstanding(@RequestParam("wid") int wid){
        UpdateWinningBidDto updateWinningBidDto = new UpdateWinningBidDto(wid);
        int bidState = 4;
        return new ResponseEntity(updateWinningBidService.updateWinningBidState(updateWinningBidDto, bidState), HttpStatus.OK);
    }
    //수령확인
    @PutMapping("/receipt")
    public ResponseEntity updateBidStateToReceipt(@RequestParam("wid") int wid){
        UpdateWinningBidDto updateWinningBidDto = new UpdateWinningBidDto(wid);
        int bidState = 3;
        return new ResponseEntity(updateWinningBidService.updateWinningBidState(updateWinningBidDto, bidState), HttpStatus.OK);
    }
    //배송시작
    @PutMapping("/shipment")
    public ResponseEntity updateBidStateToShipment(@RequestParam("wid") int wid){
        UpdateWinningBidDto updateWinningBidDto = new UpdateWinningBidDto(wid);
        int  bidState = 2;
        return new ResponseEntity(updateWinningBidService.updateWinningBidState(updateWinningBidDto, bidState), HttpStatus.OK);
    }
    //결제완료
    @PutMapping("/pay")
    public ResponseEntity updateBidStateToPay(@RequestParam("wid") int wid){
        UpdateWinningBidDto updateWinningBidDto = new UpdateWinningBidDto(wid);
        int bidState = 1;
        return new ResponseEntity(updateWinningBidService.updateWinningBidState(updateWinningBidDto, bidState), HttpStatus.OK);
    }
    //주소수정
    @PutMapping
    public ResponseEntity updateAddress(@RequestParam("address") String address, @RequestParam("wid") int wid){
        UpdateWinningBidDto updateWinningBidDto = new UpdateWinningBidDto(wid, address);
        return new ResponseEntity(updateWinningBidService.updateAddress(updateWinningBidDto), HttpStatus.OK);
    }

    //수령확인 후 완벽 종료
    @DeleteMapping
    public ResponseEntity deleteWinningBidAndAuction(DeleteWinningBidDto deleteWinningBidDto)
    {
        return new ResponseEntity(deleteWinningBidService.deleteBidAndAuction(deleteWinningBidDto), HttpStatus.OK);
    }
}
