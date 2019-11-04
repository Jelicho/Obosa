package com.ssafy.obosa.service;

import com.ssafy.obosa.exception.LowerThanCurrentBidPriceException;
import com.ssafy.obosa.exception.TimeExpiredException;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.dto.BidDto;
import com.ssafy.obosa.model.redis.Bid;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.BidRedisRepository;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class BidService {
    private final BidRedisRepository bidRedisRepository;
    private final AuctionRepository auctionRepository;

    BidService(final BidRedisRepository bidRedisRepository, AuctionRepository auctionRepository) {
        this.bidRedisRepository = bidRedisRepository;
        this.auctionRepository = auctionRepository;
    }

    public DefaultRes<Bid> newBid(BidDto bidDto, int uid) {
        Bid bidFound = null;
        try {
            bidFound = bidRedisRepository.findById(bidDto.getAid()).get();
        } catch (TimeExpiredException e) {
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.EXPIRED_AUCTION);
        } catch (LowerThanCurrentBidPriceException e) {
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.BID_LOWER_THAN_CURRENT_HIGHEST);
        } catch (NoSuchElementException e){
            LocalDateTime endTime = LocalDateTime.parse(auctionRepository.findAuctionByAid(Integer.parseInt(bidDto.getAid()))
                    .get().getEndDate());
            bidFound = Bid.builder()
                    .highestBid(0)
                    .highestBidder(uid)
                    .endTime(endTime)
                    .build();
        }finally {
            bidFound.bid(bidDto.getBidPrice(), uid, LocalDateTime.now());
            bidRedisRepository.save(bidFound);
        }
        return DefaultRes.res(StatusCode.OK, ResponseMessage.BID_SUCCESS);
    }

    public Bid findTimeExpireBid_And_deleteBid(int aid){
        try {
            Bid bidFound = bidRedisRepository.findById(Integer.toString(aid)).get();
            bidRedisRepository.delete(bidFound);
            return bidFound;
        }
        catch (NoSuchElementException e) {
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
