package com.ssafy.obosa.service;

import com.ssafy.obosa.exception.LowerThanCurrentBidPriceException;
import com.ssafy.obosa.exception.TimeExpiredException;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.dto.BidDto;
import com.ssafy.obosa.model.redis.Bid;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.BidRedisRepository;
import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BidService {
    private final BidRedisRepository bidRedisRepository;
    private final AuctionRepository auctionRepository;

    BidService(final BidRedisRepository bidRedisRepository, AuctionRepository auctionRepository) {
        this.bidRedisRepository = bidRedisRepository;
        this.auctionRepository = auctionRepository;
    }

    public DefaultRes<Bid> newBid(BidDto bidDto, int uid) {

        try {
            Bid bidFound = bidRedisRepository.findById(bidDto.getAid()).get();
            if (bidFound == null) {
                LocalDateTime endTime = LocalDateTime.parse(auctionRepository.findAuctionByAid(Integer.parseInt(bidDto.getAid()))
                                                            .get().getEndDate());
                bidFound = Bid.builder()
                            .highestBid(0)
                            .highestBidder(uid)
                            .endTime(endTime)
                            .build();
            }
            bidFound.bid(bidDto.getBidPrice(), uid, LocalDateTime.now());
            bidRedisRepository.save(bidFound);
        } catch (TimeExpiredException e) {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.EXPIRED_AUCTION);
        } catch (LowerThanCurrentBidPriceException e) {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.BID_LOWER_THAN_CURRENT_HIGHEST);
        }

        return DefaultRes.res(StatusCode.OK, ResponseMessage.BID_SUCCESS);
    }
}
