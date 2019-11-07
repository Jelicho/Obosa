package com.ssafy.obosa.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.redis.Bid;
import com.ssafy.obosa.repository.BidRedisRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AuctionDto {
    private int aid;
    private int lowPrice;
    private int highPrice;
    private String description;
    private String registeredDate;
    private String endDate;
    private char aucState;
    private int upPrice;
    private ProductDto product;
    public static AuctionDto setAuctionDtoByAuction(Auction auction)
    {
        return AuctionDto.builder()
                .aid(auction.getAid())
                .lowPrice(auction.getLowPrice())
                .highPrice(auction.getHighPrice())
                .description(auction.getDescription())
                .registeredDate(auction.getRegisteredDate().toString())
                .endDate(auction.getEndDate())
                .aucState(auction.getAucState())
                .upPrice(auction.getUpPrice())
                .product(ProductDto.setProductDtoByProduct(auction.getProduct()))
                .build();
    }
    public static Page<AuctionDto> setAuctionDtoListByAuctionList(Page<Auction> auctions, Pageable pageable, BidRedisRepository bidRedisRepository)
    {
        List<AuctionDto> auctionDtosList = new ArrayList<>();
        Bid bidFound =null;
        for(Auction auction:auctions){
            Optional<Bid> optionalBid =  bidRedisRepository.findById(auction.getAid()+"");
            System.out.println("야!");
            if(optionalBid.isPresent()){
                System.out.println("호!");
                bidFound=optionalBid.get();
                auction.setHighPrice(bidFound.getHighestBid());
            }
            auctionDtosList.add(setAuctionDtoByAuction(auction));
        }
        Page<AuctionDto> auctionDtosPage = new PageImpl<>(auctionDtosList, pageable, auctionDtosList.size());
        return auctionDtosPage;
    }
}
