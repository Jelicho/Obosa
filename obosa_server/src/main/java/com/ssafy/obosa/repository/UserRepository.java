package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>
{
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByUid(int uid);
    Optional<User> findByEmail(String email);
    User findFirstByNickname(String nickname);
    List<User> findByName(String name);
}
