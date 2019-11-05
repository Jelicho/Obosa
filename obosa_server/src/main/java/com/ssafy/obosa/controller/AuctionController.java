package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.*;
import com.ssafy.obosa.service.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("auction")
public class AuctionController {

    private final ReadAuctionService readAuctionService;
    private final CreateAuctionService createAuctionService;
    private final DeleteAuctionService deleteAuctionService;
    private final UpdateAuctionService updateAuctionService;
    private final UserService userService;
    private final BidService bidService;

    AuctionController(final ReadAuctionService readAuctionService,
                      final CreateAuctionService createAuctionService, final DeleteAuctionService deleteAuctionService,
                      final UpdateAuctionService updateAuctionService, final UserService userService,
                      final BidService bidService){
        this.readAuctionService=readAuctionService;
        this.createAuctionService=createAuctionService;
        this.deleteAuctionService=deleteAuctionService;
        this.updateAuctionService=updateAuctionService;
        this.userService = userService;
        this.bidService = bidService;
    }

    @PostMapping("/bid")
    public ResponseEntity bidAuction(@RequestHeader(value = "Authorization", required = false) String jwtToken, BidDto bidDto) {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user==null){
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            int uid = user.getUid();
            return new ResponseEntity(bidService.newBid(bidDto, uid), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{aid}")
    public ResponseEntity readAuction(@PathVariable("aid") int aid)
    {
        ReadAuctionDto readAuctionDto = new ReadAuctionDto(aid);
        return new ResponseEntity(readAuctionService.readAuctionByAid(readAuctionDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity readAllAuction(Pageable pageable)
    {
        return new ResponseEntity(readAuctionService.readAllAuctions(pageable), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity readAll(@RequestParam String type, @RequestParam String searchStr, Pageable pageable)
    {
        ReadAuctionDto readAuctionDto = new ReadAuctionDto(type, searchStr);
        return new ResponseEntity(readAuctionService.readSearchByType(readAuctionDto, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createAuction(@RequestHeader(value = "Authorization", required = false) String jwtToken, CreateAuctionDto createAuctionDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(createAuctionService.newAuction(user, createAuctionDto), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAuction(@RequestHeader(value = "Authorization", required = false) String jwtToken, DeleteAuctionDto deleteAuctionDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(deleteAuctionService.deleteAuction(user, deleteAuctionDto), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity updateAuction(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateAuctionDto updateAuctionDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(updateAuctionService.updateAuction(user, updateAuctionDto), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
