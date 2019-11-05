package com.ssafy.obosa.model.redis;

import com.ssafy.obosa.exception.LowerThanCurrentBidPriceException;
import com.ssafy.obosa.exception.TimeExpiredException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("bid")
@ToString
public class Bid implements Serializable {

    @Id
    private String id;
    private int highestBid;
    private int highestBidder; //uid
    private LocalDateTime endTime;
    private int bidCount;

    public Bid(String id, int highestBid, int highestBidder, LocalDateTime endTime) {
        this(id, highestBid, highestBidder, endTime, 0);
    }

    public void bid(int bidPrice, int bidder, LocalDateTime bidTime) {
        if (bidTime.isAfter(this.endTime)) {
            throw new TimeExpiredException("경매 시간이 종료되었습니다.");
        }
        if (bidPrice <= this.highestBid) {
            throw new LowerThanCurrentBidPriceException("현재 최고 입찰액보다 큰 금액으로 상회 입찰할 수 있습니다.");
        }

        this.highestBid = bidPrice;
        this.highestBidder = bidder;
        this.bidCount++;
    }
}
