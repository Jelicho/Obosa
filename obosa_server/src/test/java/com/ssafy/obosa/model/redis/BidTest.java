package com.ssafy.obosa.model.redis;

import com.ssafy.obosa.exception.LowerThanCurrentBidPriceException;
import com.ssafy.obosa.exception.TimeExpiredException;
import com.ssafy.obosa.repository.BidRedisRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTest {

    @Autowired
    private BidRedisRepository bidRedisRepository;

    @After
    public void tearDown() throws Exception {
        bidRedisRepository.deleteAll();
    }

    @Test
    public void 기본_등록_조회기능() {
        // given
        String aid = "1";
        LocalDateTime endTime = LocalDateTime.now();
        int highestBid = 10000;
        int highestBidder = 1;

        Bid bid = Bid.builder()
                    .id(aid)
                    .highestBid(highestBid)
                    .highestBidder(highestBidder)
                    .endTime(endTime)
                    .build();

        // when
        bidRedisRepository.save(bid);

        // then
        Bid savedBid = bidRedisRepository.findById(aid).get();

        assertThat(savedBid.getHighestBid()).isEqualTo(10000);
        assertThat(savedBid.getHighestBidder()).isEqualTo(highestBidder);
    }

    @Test(expected = LowerThanCurrentBidPriceException.class)
    public void 입찰_금액_검사기능() {
        // given
        String aid = "1";
        LocalDateTime endTime = LocalDateTime.now();
        int highestBid = 10000;
        int highestBidder = 1;

        Bid bid = Bid.builder()
                .id(aid)
                .highestBid(highestBid)
                .highestBidder(highestBidder)
                .endTime(endTime)
                .build();

        bidRedisRepository.save(bid);
        Bid savedBid = bidRedisRepository.findById("1").get();

        // when
        savedBid.bid(5000, 2, LocalDateTime.now().minusDays(1));

        // then
    }

    @Test(expected = TimeExpiredException.class)
    public void 입찰_시간_검사기능() {
        // given
        String aid = "1";
        LocalDateTime endTime = LocalDateTime.now();
        int highestBid = 10000;
        int highestBidder = 1;

        Bid bid = Bid.builder()
                .id(aid)
                .highestBid(highestBid)
                .highestBidder(highestBidder)
                .endTime(endTime)
                .build();

        bidRedisRepository.save(bid);
        Bid savedBid = bidRedisRepository.findById("1").get();

        // when
        savedBid.bid(12000, 2, LocalDateTime.now().plusDays(1));

        // then
    }

    @Test
    public void 입찰_기능() {
        // given
        String aid = "1";
        LocalDateTime endTime = LocalDateTime.now();
        int highestBid = 10000;
        int highestBidder = 1;

        Bid bid = Bid.builder()
                .id(aid)
                .highestBid(highestBid)
                .highestBidder(highestBidder)
                .endTime(endTime)
                .build();

        bidRedisRepository.save(bid);
        Bid savedBid = bidRedisRepository.findById("1").get();

        // when
        savedBid.bid(12000, 2, LocalDateTime.now().minusDays(1));
        bidRedisRepository.save(savedBid);

        // then
        Bid afterBidding = bidRedisRepository.findById("1").get();
        assertThat(afterBidding.getHighestBid()).isEqualTo(12000);
        assertThat(afterBidding.getHighestBidder()).isEqualTo(2);
    }
}