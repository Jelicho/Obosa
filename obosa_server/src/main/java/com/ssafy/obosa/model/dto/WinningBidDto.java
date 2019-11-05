package com.ssafy.obosa.model.dto;

import com.ssafy.obosa.model.domain.WinningBid;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
public class WinningBidDto {
    private int wid;
    private int bidPrice;
    private String bidDate;
    private int bidState;
    private String address;
    private AuctionDto auction;
    private UserDto user;
    public static WinningBidDto setWinningBidDtoByWinningBid(WinningBid winningBid)
    {
        return WinningBidDto.builder()
                .wid(winningBid.getWid())
                .bidPrice(winningBid.getBidPrice())
                .bidDate(winningBid.getBidDate())
                .address(winningBid.getAddress())
                .auction(AuctionDto.setAuctionDtoByAuction(winningBid.getAuction()))
                .user(UserDto.setUserDtoByUser(winningBid.getUser()))
                .build();
    }
    public static Page<WinningBidDto> setProductDtoListByProductList(Page<WinningBid> winningBids, Pageable pageable)
    {
        List<WinningBidDto> winningBidDtosList = new ArrayList<>();
        for(WinningBid winningBid:winningBids){
            winningBidDtosList.add(setWinningBidDtoByWinningBid(winningBid));
        }
        Page<WinningBidDto> winningBidDtosPage = new PageImpl<>(winningBidDtosList, pageable, winningBidDtosList.size());
        return winningBidDtosPage;
    }
}
