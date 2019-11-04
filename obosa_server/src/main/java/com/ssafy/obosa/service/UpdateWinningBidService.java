package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.domain.WinningBid;
import com.ssafy.obosa.model.dto.UpdateWinningBidDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.repository.WinningBidRepository;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateWinningBidService {
    private final WinningBidRepository winningBidRepository;
    private final UserRepository userRepository;
    UpdateWinningBidService(final WinningBidRepository winningBidRepository, final UserRepository userRepository){
        this.winningBidRepository = winningBidRepository;
        this.userRepository = userRepository;
    }

    public DefaultRes<UpdateWinningBidDto> updateAddress(User user, UpdateWinningBidDto updateWinningBidDto)
    {
        try{
            int wid = updateWinningBidDto.getWid();
            String address = updateWinningBidDto.getAddress();
            Optional<WinningBid> optionalWinningBid = winningBidRepository.findWinningBidByWid(wid);
            if(!optionalWinningBid.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_WINNINGBID);
            }
            WinningBid winningBid = optionalWinningBid.get();
            if(winningBid.getBidState()!=0){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.ALREADY_PASS_BIDSTATE);
            }
            else if(winningBid.getUser()!=user){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
            }
            winningBid.setAddress(address);
            winningBidRepository.save(winningBid);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_WINNINGBID_ADDRESS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
    public DefaultRes<UpdateWinningBidDto> updateWinningBidState(User user, UpdateWinningBidDto updateWinningBidDto, int bidState)
    {
        try{
            int wid = updateWinningBidDto.getWid();
            Optional<WinningBid> optionalWinningBid = winningBidRepository.findWinningBidByWid(wid);
            if(!optionalWinningBid.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_WINNINGBID);
            }
            WinningBid winningBid = optionalWinningBid.get();
            if(winningBid.getBidState()<=bidState){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.ALREADY_PASS_BIDSTATE);
            }
//            -결제완료처리 : 구매자만 가능
//            -배송중 처리 : 판매자만 가능
//            -수령확인 : 구매자만 가능
//            -결제취소 : 구매자만 가능(단, 3일 판매자도 가능하다.)
            User seller = winningBid.getAuction().getUser();
            User winner = winningBid.getUser();
            switch(bidState){
                case 1:
                    if(winner!=user)return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
                    break;
                case 2:
                    if(seller!=user)return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
                    break;
                case 3:
                    if(winner!=user)return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
                    break;
                case 4:
                    if(winner!=user)return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
                    else{
                        DateTime bidDate = new DateTime(winningBid.getBidDate());
                        DateTime banDatetime = bidDate.plusDays(3);

                        Date now = new Date();
                        DateTime nowDateTime = new DateTime(now);
                        //nowDateTime is before banDatetime
                        if(nowDateTime.compareTo(banDatetime)<0){
                            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
                        }
                    }
                    break;
            }
            winningBid.setBidState(bidState);
            winningBidRepository.save(winningBid);
            if(bidState == 4){
                Optional<User> optionalUser = userRepository.findByUid(winningBid.getUser().getUid());
                User banUser = optionalUser.get();
                banUser.setWithDraw(true);
                userRepository.save(banUser);
            }
            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_WINNINGBID_STATE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
}
