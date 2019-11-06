package com.ssafy.obosa.service;

import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.dto.AuctionDto;
import com.ssafy.obosa.model.dto.ReadAuctionDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.BidRedisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReadAuctionService {
    private final AuctionRepository auctionRepository;
    private final BidRedisRepository bidRedisRepository;
    public ReadAuctionService(final AuctionRepository auctionRepository, final BidRedisRepository bidRedisRepository)
    {
        this.auctionRepository = auctionRepository;
        this.bidRedisRepository = bidRedisRepository;
    }

    @Transactional(readOnly = true)
    public DefaultRes<AuctionDto> readAuctionByAid(ReadAuctionDto readAuctionDto)
    {
        Optional<Auction> optionalAuction = auctionRepository.findAuctionByAid(readAuctionDto.getAid());
        if(!optionalAuction.isPresent()){
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_AUCTION);
        }
        Auction auction = optionalAuction.get();

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_AUCTION, AuctionDto.setAuctionDtoByAuction(auction));
    }

    @Transactional(readOnly = true)
    public DefaultRes<Page<AuctionDto>> readAllAuctions(Pageable pageable)
    {
        Page<Auction> auctions = auctionRepository.findAll(pageable);
        if(auctions.isEmpty()){
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_SEARCH);
        }else{
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_ALL_AUCTIONS, AuctionDto.setAuctionDtoListByAuctionList(auctions, pageable, bidRedisRepository));
        }
    }

    @Transactional(readOnly = true)
    public DefaultRes<Page<AuctionDto>> readSearchByType(ReadAuctionDto readAuctionDto, Pageable pageable)
    {
        Page<Auction> auctions;
        //pname, nickname으로 검색
        switch(readAuctionDto.getType()){
            case "pname":
                auctions = auctionRepository.findByProduct_PnameContaining(readAuctionDto.getSearchStr(), pageable);
                break;
            case "nickname":
                auctions = auctionRepository.findByUser_NicknameContaining(readAuctionDto.getSearchStr(), pageable);
                break;
            default:
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.TYPE_ERROR);
        }
        if(auctions.isEmpty()){
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_SEARCH);
        }else{
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_SEARCH_AUCTIONS, AuctionDto.setAuctionDtoListByAuctionList(auctions, pageable, bidRedisRepository));
        }
    }

}
