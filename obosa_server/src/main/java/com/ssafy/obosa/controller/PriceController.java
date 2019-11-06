package com.ssafy.obosa.controller;

import com.ssafy.obosa.model.dto.AuctionIdDto;
import com.ssafy.obosa.model.dto.PriceDto;
import com.ssafy.obosa.repository.BidRedisRepository;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PriceController 
{
    private final BidRedisRepository bidRedisRepository;
    private SimpMessagingTemplate messagingTemplate;
    PriceController(final BidRedisRepository bidRedisRepository) {
        this.bidRedisRepository = bidRedisRepository;
        // this.messagingTemplate = messagingTemplate;
    }
    @SendTo("/topic/notification")
    @MessageMapping("/hello")
    public PriceDto getHighPrice(String id) throws Exception
    {
         int highestPrice = bidRedisRepository.findById(id).get().getHighestBid();
        //  messagingTemplate.convertAndSend("/api/topic/price", "리민경 동무 안녕하신가");
        return new PriceDto(id,highestPrice);
    }
}