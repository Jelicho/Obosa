package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.CreateAuctionDto;
import com.ssafy.obosa.model.dto.CreateProductDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateAuctionService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    CreateAuctionService(final AuctionRepository auctionRepository, final UserRepository userRepository, final ProductRepository productRepository){
        this.auctionRepository=auctionRepository;
        this.userRepository=userRepository;
        this.productRepository=productRepository;
    }

    public DefaultRes<CreateProductDto> newAuction(CreateAuctionDto createAuctionDto){
        try{
            int pid = createAuctionDto.getPid();
            Optional<Product> optionalProduct = productRepository.findByPid(pid);
            if(!optionalProduct.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_PRODUCT);
            }

            Product product = optionalProduct.get();
            User user = product.getUser();

            Auction auction = Auction.setAuctionByAuctionDto(createAuctionDto, user, product);

            auctionRepository.save(auction);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_AUCTION);

        }catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }

}
