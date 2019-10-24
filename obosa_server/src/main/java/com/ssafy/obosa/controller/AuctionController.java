package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.dto.CreateAuctionDto;
import com.ssafy.obosa.model.dto.DeleteAuctionDto;
import com.ssafy.obosa.model.dto.UpdateAuctionDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.service.CreateAuctionService;
import com.ssafy.obosa.service.DeleteAuctionService;
import com.ssafy.obosa.service.UpdateAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auction")
public class AuctionController {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    CreateAuctionService createAuctionService;

    @Autowired
    DeleteAuctionService deleteAuctionService;

    @Autowired
    UpdateAuctionService updateAuctionService;
//    @GetMapping("/{aid}")
//    public ResponseEntity readAuction(ReadAuctionService readAuctionService, @PathVariable("aid") int aid)
//    {
//        return new ResponseEntity(readAuctionService.readOneAuctionByAid(aid), HttpStatus.OK);
//    }
//
//    @GetMapping("/{startingIndex}")
//    public ResponseEntity readAuctions(ReadAuctionService readAuctionService, @PathVariable("startingIndex") int startingIndex)
//    {
//        return new ResponseEntity(readAuctionService.readAllAuctions(startingIndex), HttpStatus.OK);
//    }



    @PostMapping
    public ResponseEntity createAuction(CreateAuctionDto createAuctionDto)
    {
        return new ResponseEntity(createAuctionService.newAuction(createAuctionDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAuction(DeleteAuctionDto deleteAuctionDto)
    {
        return new ResponseEntity(deleteAuctionService.deleteAuction(deleteAuctionDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateAuction(UpdateAuctionDto updateAuctionDto)
    {
        return new ResponseEntity(updateAuctionService.updateAuction(updateAuctionDto), HttpStatus.OK);
    }
}
