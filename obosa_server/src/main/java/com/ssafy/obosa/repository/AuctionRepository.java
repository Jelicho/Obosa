package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, String> {
    Optional<Auction> findAuctionByAid(int aid);
    Page<Auction> findAll(Pageable pageable);
    Page<Auction> findByProduct_PnameContaining(String searchStr, Pageable pageable);
    Page<Auction> findByUser_NicknameContaining(String searchStr, Pageable pageable);
}
