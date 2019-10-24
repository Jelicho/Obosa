package com.ssafy.obosa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuctionDto {
    private int aid;
    private int lowPrice;
    private int highPrice;
    private String description;
    private String endDate;
    private char aucState;
    private int upPrice;
}
