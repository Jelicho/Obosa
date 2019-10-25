package com.ssafy.obosa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuctionDto {
    private int pid;
    private int lowPrice;
    private int highPrice;
    private String description;
    private String endDate;
    private char aucState;
    private int upPrice;


    CreateAuctionDto(int pid, int lowPrice, String description, String endDate){
        this.pid = pid;
        this.lowPrice = lowPrice;
        this.highPrice = 2000000000;
        this.description=description;
        this.endDate=endDate;
        this.aucState=0;
        this.upPrice=1000;
    }
}
