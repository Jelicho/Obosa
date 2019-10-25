package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.dto.CreateAuctionDto;
import com.ssafy.obosa.model.dto.DeleteAuctionDto;
import com.ssafy.obosa.model.dto.ReadAuctionDto;
import com.ssafy.obosa.model.dto.UpdateAuctionDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.service.CreateAuctionService;
import com.ssafy.obosa.service.DeleteAuctionService;
import com.ssafy.obosa.service.ReadAuctionService;
import com.ssafy.obosa.service.UpdateAuctionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("auction")
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final ReadAuctionService readAuctionService;
    private final CreateAuctionService createAuctionService;
    private final DeleteAuctionService deleteAuctionService;
    private final UpdateAuctionService updateAuctionService;
    AuctionController(final AuctionRepository auctionRepository, final ReadAuctionService readAuctionService,
                      final CreateAuctionService createAuctionService, final DeleteAuctionService deleteAuctionService,
                      final UpdateAuctionService updateAuctionService){
        this.auctionRepository=auctionRepository;
        this.readAuctionService=readAuctionService;
        this.createAuctionService=createAuctionService;
        this.deleteAuctionService=deleteAuctionService;
        this.updateAuctionService=updateAuctionService;
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
