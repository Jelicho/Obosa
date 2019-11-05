package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {
    Optional<Auction> findAuctionByAid(int aid);
    Page<Auction> findAll(Pageable pageable);
    Page<Auction> findByProduct_PnameContaining(String searchStr, Pageable pageable);
    Page<Auction> findByUser_NicknameContaining(String searchStr, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Auction auc set auc.aucState = ?1 where auc.endDate <= ?2", nativeQuery = true)
    int setFixedAucStateFromNowDateTime(char aucState, String nowDateTime);
    @Transactional
    long deleteByAid(int aid);
    @Query(value = "SELECT * FROM AUCTION A WHERE A.aucState = ?1 and A.ENDDATE >= ?2", nativeQuery = true)
    List<Auction> findByAucStateAndNowDate(
            char aucState,
            String nowDate);
}
