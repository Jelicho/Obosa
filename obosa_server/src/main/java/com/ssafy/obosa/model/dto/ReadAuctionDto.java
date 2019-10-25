package com.ssafy.obosa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadAuctionDto {


    private int aid;

    private String type;
    private String searchStr;

    public ReadAuctionDto(int aid) {
        this.aid = aid;
    }

    public ReadAuctionDto(String type, String searchStr) {
        this.type = type;
        this.searchStr = searchStr;
    }
}
