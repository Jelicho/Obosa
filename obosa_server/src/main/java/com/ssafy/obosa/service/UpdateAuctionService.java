package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.UpdateAuctionDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAuctionService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;

    public UpdateAuctionService(final ProductRepository productRepository, final UserRepository userRepository, final AuctionRepository auctionRepository)
    {
        this.productRepository = productRepository;
        this.userRepository=userRepository;
        this.auctionRepository = auctionRepository;
    }
    public DefaultRes<UpdateAuctionDto> updateAuction(User user, UpdateAuctionDto updateProductDto){
        int aid = updateProductDto.getAid();
        Optional<Auction> optionalAuction = auctionRepository.findAuctionByAid(aid);
        if(!optionalAuction.isPresent()){
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_AUCTION);
        }
        Auction auction = optionalAuction.get();
        if(auction.getUser()!=user){
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
        }
        Auction.updateAuctionByAuctionDto(auction, updateProductDto);
        auctionRepository.save(auction);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_AUCTION);
    }
}
