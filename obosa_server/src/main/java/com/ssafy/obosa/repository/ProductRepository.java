package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String>
{
    Page<Product> findByUser(User user, Pageable pageable);
    Page<Product> findByPnameContaining(String pname, Pageable pageable);
    Optional<Product> findByPid(int pid);
    @Transactional
    long deleteByPid(int pid);
}
