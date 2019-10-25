package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import com.ssafy.obosa.model.dto.CreateAuctionDto;
import com.ssafy.obosa.model.dto.UpdateAuctionDto;
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
public class Auction extends DateEntity
{

    @Id
    private int aid;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    @OneToOne
    @JoinColumn(name = "pid")
    private Product product;

    @NotNull
    private int lowPrice;

    @ColumnDefault("2000000000")
    private int highPrice;

    @NotNull
    private String description;

    @NotNull
    private String endDate;

    @ColumnDefault("0")
    private char aucState;

    @ColumnDefault("1000")
    private int upPrice;

    public static Auction setAuctionByAuctionDto(CreateAuctionDto createAuctionDto, User user, Product product)
    {
        return Auction.builder()
                .lowPrice(createAuctionDto.getLowPrice())
                .highPrice(createAuctionDto.getHighPrice())
                .description(createAuctionDto.getDescription())
                .endDate(createAuctionDto.getEndDate())
                .aucState(createAuctionDto.getAucState())
                .upPrice(createAuctionDto.getUpPrice())
                .user(user)
                .product(product)
                .build();
    }

    public static void updateAuctionByAuctionDto(Auction auction, UpdateAuctionDto updateAuctionDto)
    {
        auction.setAucState(updateAuctionDto.getAucState());
        auction.setDescription(updateAuctionDto.getDescription());
        auction.setEndDate(updateAuctionDto.getEndDate());
        auction.setHighPrice(updateAuctionDto.getHighPrice());
        auction.setLowPrice(updateAuctionDto.getLowPrice());
        auction.setUpPrice(updateAuctionDto.getUpPrice());
    }
}
