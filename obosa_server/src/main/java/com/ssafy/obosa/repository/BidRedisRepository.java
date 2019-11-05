package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.redis.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRedisRepository extends CrudRepository<Bid, String> {
}
