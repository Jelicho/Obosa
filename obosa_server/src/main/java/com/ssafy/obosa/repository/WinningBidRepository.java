package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.domain.WinningBid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface WinningBidRepository extends JpaRepository<WinningBid, String> {
    Optional<WinningBid> findWinningBidByWid(int wid);

    @Transactional
    @Modifying
    @Query(value = "update WinningBid WB set WB.bidState = ?1 where WB.wid = ?2", nativeQuery = true)
    int setFixedBidStateFromWid(int bidState, int wid);

    @Transactional
    @Modifying
    @Query(value = "update WinningBid WB set WB.address = ?1 where WB.wid = ?2", nativeQuery = true)
    int setFixedAddressFromWid(String address, int wid);

    @Transactional
    long deleteByWid(int wid);

    Page<WinningBid> findByUser(User user, Pageable pageable);
    Page<WinningBid> findByAuction_User(User user, Pageable pageable);
}
