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

    public DefaultRes<UpdateWinningBidDto> updateAddress(UpdateWinningBidDto updateWinningBidDto)
    {
        try{
            int wid = updateWinningBidDto.getWid();
            String address = updateWinningBidDto.getAddress();
            winningBidRepository.setFixedAddressFromWid(address, wid);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_WINNINGBID_ADDRESS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
    public DefaultRes<UpdateWinningBidDto> updateWinningBidState(UpdateWinningBidDto updateWinningBidDto, int bidState)
    {
        try{
            int wid = updateWinningBidDto.getWid();
            Optional<WinningBid> optionalWinningBid = winningBidRepository.findWinningBidByWid(wid);
            if(!optionalWinningBid.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_WINNINGBID);
            }
            winningBidRepository.setFixedBidStateFromWid(bidState, wid);
            if(bidState == 4){
                WinningBid winningBid = optionalWinningBid.get();
                Optional<User> optionalUser = userRepository.findByUid(winningBid.getUser().getUid());
                User user = optionalUser.get();
                userRepository.setFixedWithDrawFromUid(user.getUid());
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
