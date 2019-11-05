package com.ssafy.obosa.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    private final WinningBidUtil winningBidUtil;
    Scheduler(final WinningBidUtil winningBidUtil){
        this.winningBidUtil = winningBidUtil;
    }
    @Scheduled(cron = "0 0 * * * *")
    public void auctionClose() {
        System.out.println("낙찰 Scheduler 동작 사작");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowDateTime = sdf.format(now);
        try {
            winningBidUtil.CreateWinningBid(nowDateTime);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("낙찰 Scheduler 동작 완료");
        }
    }
}