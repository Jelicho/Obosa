package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.dto.AuctionIdDto;
import com.ssafy.obosa.model.dto.PriceDto;
import com.ssafy.obosa.repository.BidRedisRepository;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class PriceController 
{
    private final BidRedisRepository bidRedisRepository;
    PriceController(final BidRedisRepository bidRedisRepository) {
        this.bidRedisRepository = bidRedisRepository;
    }
    @SendTo("/api/topic/bidPrice")
    @MessageMapping("/bidPrice")
    // public PriceDto getHighPrice(String id) throws Exception
    // {
    //      int highestPrice = bidRedisRepository.findById(id).get().getHighestBid();
    //     return new PriceDto(id,highestPrice);
    // }
    public PriceDto getHighPrice(AuctionIdDto auctionId) throws Exception
    {
        int highestPrice = bidRedisRepository.findById(auctionId.getId()).get().getHighestBid();
        return new PriceDto(auctionId.getId(),highestPrice);
    }
}