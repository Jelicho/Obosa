package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.domain.WinningBid;
import com.ssafy.obosa.model.dto.ReadWinningBidDto;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.repository.WinningBidRepository;
import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadWinningBidService {
    private final WinningBidRepository winningBidRepository;
    private final UserRepository userRepository;
    ReadWinningBidService(final WinningBidRepository winningBidRepository, final UserRepository userRepository){
        this.winningBidRepository = winningBidRepository;
        this.userRepository = userRepository;
    }
    public DefaultRes<WinningBid> readWinningBid(ReadWinningBidDto readWinningBidDto){
        try{
            int wid = readWinningBidDto.getId();
            Optional<WinningBid> optionalWinningBid = winningBidRepository.findWinningBidByWid(wid);
            if(!optionalWinningBid.isPresent()){
                return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_WINNINGBID);
            }else{
                return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_WINNINGBID, optionalWinningBid.get());
            }
        }catch (Exception e){
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
    public DefaultRes<Page<WinningBid>> readWinningBidsForWinner(ReadWinningBidDto readWinningBidDto, Pageable pageable)
    {
        try{
            int uid = readWinningBidDto.getId();
            Optional<User> optionalUser = userRepository.findByUid(uid);
            if(!optionalUser.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER);
            }else{
                User user = optionalUser.get();
                Page<WinningBid> winningBids = winningBidRepository.findByUser(user, pageable);
                return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_WINNINGBID_WINNER, winningBids);
            }
        }catch (Exception e){
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
    public DefaultRes<Page<WinningBid>> readWinningBidsForSeller(ReadWinningBidDto readWinningBidDto, Pageable pageable)
    {
        try{
            int uid = readWinningBidDto.getId();
            Optional<User> optionalUser = userRepository.findByUid(uid);
            if(!optionalUser.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER);
            }else{
                User user = optionalUser.get();
                Page<WinningBid> winningBids = winningBidRepository.findByAuction_User(user, pageable);
                return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_WINNINGBID_SELLER, winningBids);
            }
        }catch (Exception e){
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
}
