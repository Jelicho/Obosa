package com.ssafy.obosa.model.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WinningBid {
    @Id
    private int wid;

    @NotNull
    @OneToOne
    @JoinColumn(name = "aid")
    private Auction auction;

    @NotNull
    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    @NotNull
    private int bidPrice;

    @NotNull
    private String bidDate;

    @NotNull
    @ColumnDefault("0")
    private int bidState;

    @NotNull
    private String address;

    public WinningBid(@NotNull Auction auction, @NotNull User user, @NotNull int bidPrice, @NotNull int bidState, @NotNull String address) {
        this.auction = auction;
        this.user = user;
        this.bidPrice = bidPrice;
        this.bidState = bidState;
        this.address = address;
    }
}
