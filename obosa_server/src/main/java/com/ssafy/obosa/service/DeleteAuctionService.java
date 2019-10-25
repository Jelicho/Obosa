package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.dto.DeleteAuctionDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteAuctionService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    DeleteAuctionService(final AuctionRepository auctionRepository, final UserRepository userRepository, final ProductRepository productRepository){
        this.auctionRepository=auctionRepository;
        this.userRepository=userRepository;
        this.productRepository=productRepository;
    }

    public DefaultRes<DeleteAuctionDto> deleteAuction(DeleteAuctionDto deleteAuctionDto){
            int aid = deleteAuctionDto.getAid();

            Optional<Auction> optionalAuction = auctionRepository.findAuctionByAid(aid);
            if(!optionalAuction.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_AUCTION);
            }

            Auction auction = optionalAuction.get();

            auctionRepository.delete(auction);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETED_AUCTION);
    }
}
