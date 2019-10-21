package com.ssafy.obosa.controller;


import com.ssafy.obosa.model.dto.ProductRegistrationDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.ProductRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("ProductRegistration")
public class ProductRegistrationController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductRegistrationService productRegistrationService;

    @PostMapping
    public ResponseEntity ProductRegistration(ProductRegistrationDto productRegistrationDto, @RequestPart(value="productImgs", required = false) final List<MultipartFile> productImgs)
    {
        return new ResponseEntity(productRegistrationService.newProduct(productRegistrationDto, productImgs), HttpStatus.OK);
    }
}
