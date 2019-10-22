package com.ssafy.obosa.repository;

import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String>
{
    List<Product> findByUser(User user);
    List<Product> findByPnameContaining(String pname);
    Optional<Product> findByPid(int pid);
}
