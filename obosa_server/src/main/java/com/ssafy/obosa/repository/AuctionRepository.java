package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, String> {
    Optional<User> findUserByAid(int aid);
    Optional<Product> findProductByAid(int aid);
    Optional<Auction> findAuctionByAid(int aid);
}
