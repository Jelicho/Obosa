package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.redis.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRedisRepository extends CrudRepository<Bid, String> {
}
