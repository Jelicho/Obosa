package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.domain.WinningBid;
import com.ssafy.obosa.model.dto.DeleteWinningBidDto;
import com.ssafy.obosa.repository.AuctionRepository;
import com.ssafy.obosa.repository.WinningBidRepository;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteWinningBidService {
    private final AuctionRepository auctionRepository;
    private final WinningBidRepository winningBidRepository;
    DeleteWinningBidService(final AuctionRepository auctionRepository, final WinningBidRepository winningBidRepository){
        this.auctionRepository=auctionRepository;
        this.winningBidRepository=winningBidRepository;
    }

    public DefaultRes<DeleteWinningBidDto> deleteBidAndAuction(User user, DeleteWinningBidDto deleteWinningBidDto){
        try
        {
            //삭제할 WinningBid  객체 가져오기
            int wid = deleteWinningBidDto.getWid();
            Optional<WinningBid> optionalWinningBid = winningBidRepository.findWinningBidByWid(wid);

            //유효성 검사
            if(!optionalWinningBid.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_WINNINGBID);
            }

            WinningBid winningBid = optionalWinningBid.get();
            if(winningBid.getAuction().getUser()!=user){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
            }
            winningBidRepository.delete(winningBid);
            auctionRepository.delete(winningBid.getAuction());

            return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETED_WINNINGBID_AND_AUCTION);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }

    }
}
