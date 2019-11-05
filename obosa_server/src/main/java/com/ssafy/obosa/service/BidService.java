package com.ssafy.obosa.service;

import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import com.ssafy.obosa.exception.LowerThanCurrentBidPriceException;
import com.ssafy.obosa.exception.TimeExpiredException;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.dto.BidDto;
import com.ssafy.obosa.model.redis.Bid;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.BidRedisRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        LocalDateTime endTime=null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            endTime = LocalDateTime.parse(auctionRepository.findAuctionByAid(Integer.parseInt(bidDto.getAid()))
                    .get().getEndDate(), formatter);
            Optional<Bid> optionalBid =  bidRedisRepository.findById(bidDto.getAid());
            if(!optionalBid.isPresent()){
                bidFound = Bid.builder()
                        .id(bidDto.getAid())
                        .highestBid(0)
                        .highestBidder(uid)
                        .endTime(endTime)
                        .build();
            }else{
                bidFound = optionalBid.get();
            }
            bidFound.bid(bidDto.getBidPrice(), uid, LocalDateTime.now());
            bidRedisRepository.save(bidFound);
        }  catch (TimeExpiredException e) {
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.EXPIRED_AUCTION);
        } catch (LowerThanCurrentBidPriceException e) {
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.BID_LOWER_THAN_CURRENT_HIGHEST);
        }catch (Exception e){
            e.printStackTrace();
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
