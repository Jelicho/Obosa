package com.ssafy.obosa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWinningBidDto {
    private int wid;
    private String address;

    public UpdateWinningBidDto(int wid) {
        this.wid = wid;
    }
}
