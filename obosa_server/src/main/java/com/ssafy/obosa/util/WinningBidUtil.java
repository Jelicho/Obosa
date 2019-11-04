package com.ssafy.obosa.util;

import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.domain.WinningBid;
import com.ssafy.obosa.model.redis.Bid;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.repository.WinningBidRepository;
import com.ssafy.obosa.service.BidService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WinningBidUtil {
    private final WinningBidRepository winningBidRepository;
    private final AuctionRepository auctionRepository;
    private final BidService bidService;
    private final UserRepository userRepository;
    WinningBidUtil(final WinningBidRepository winningBidRepository, final AuctionRepository auctionRepository, final BidService bidService
                   , final UserRepository userRepository){
        this.winningBidRepository = winningBidRepository;
        this.auctionRepository = auctionRepository;
        this.bidService = bidService;
        this.userRepository = userRepository;
    }

    @Transactional
    void CreateWinningBid(String nowDate)throws Exception{
        List<Auction> expireAuctions = auctionRepository.findByAucStateAndNowDate('0', nowDate);
        for(Auction auction:expireAuctions){
            Bid winningBid = bidService.findTimeExpireBid_And_deleteBid(auction.getAid());
            if(winningBid==null){
                auction.setAucState('2');
                auctionRepository.save(auction);
            }else{
                auction.setAucState('3');
                auctionRepository.save(auction);

                User highestUser = userRepository.findByUid(winningBid.getHighestBid()).get();
                int bidPrice = winningBid.getHighestBid();
                WinningBid newWinningBid = new WinningBid(auction, highestUser, bidPrice, 0, highestUser.getAddress());
                winningBidRepository.save(newWinningBid);
            }
        }
    }
}
