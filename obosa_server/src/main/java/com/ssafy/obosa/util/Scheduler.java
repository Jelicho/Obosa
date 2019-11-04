package com.ssafy.obosa.util;

import com.ssafy.obosa.service.DeleteProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    private final DeleteProductService deleteProductService;
    Scheduler(DeleteProductService deleteProductService){
        this.deleteProductService=deleteProductService;
    }
    @Scheduled(cron = "0 0 * * * *")
    public void auctionClose() {
        //enddate를 통해 끝난 경매 찾기=>aid 가져오기
        //updateAuctionService에 존재하는 낙찰 method에 aid넘기기(낙찰)->insert winningbid

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowDateTime = sdf.format(now);
        System.out.println("Java cron job expression:: " + nowDateTime);
    }
}