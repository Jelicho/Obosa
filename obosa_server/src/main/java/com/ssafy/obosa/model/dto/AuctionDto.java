package com.ssafy.obosa.model.dto;

import com.ssafy.obosa.model.domain.Auction;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@Builder
@ToString
public class AuctionDto {
    private int aid;
    private int lowPrice;
    private int highPrice;
    private String description;
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
                .endDate(auction.getEndDate())
                .aucState(auction.getAucState())
                .upPrice(auction.getUpPrice())
                .product(ProductDto.setProductDtoByProduct(auction.getProduct()))
                .build();
    }
    public static Page<AuctionDto> setAuctionDtoListByAuctionList(Page<Auction> auctions, Pageable pageable)
    {
        List<AuctionDto> auctionDtosList = null;
        for(Auction auction:auctions){
            auctionDtosList.add(setAuctionDtoByAuction(auction));
        }
        Page<AuctionDto> auctionDtosPage = new PageImpl<>(auctionDtosList, pageable, auctionDtosList.size());
        return auctionDtosPage;
    }
}
