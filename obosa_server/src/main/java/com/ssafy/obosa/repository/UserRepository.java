package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>
{
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByUid(int uid);
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    User findUserByNickname(String nickname);
    List<User> findByName(String name);
    @Modifying
    @Query(value = "update USER u set u.withDraw = TRUE where u.uid = ?1", nativeQuery = true)
    int setFixedWithDrawFromUid(int uid);
}
