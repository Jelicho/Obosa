package com.ssafy.obosa.controller;


import com.ssafy.obosa.model.dto.CreateProductDto;
import com.ssafy.obosa.model.dto.DeleteProductDto;
import com.ssafy.obosa.model.dto.ReadProductDto;
import com.ssafy.obosa.model.dto.UpdateProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.CreateProductService;
import com.ssafy.obosa.service.DeleteProductService;
import com.ssafy.obosa.service.ReadProductService;
import com.ssafy.obosa.service.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReadProductService readProductService;

    @Autowired
    CreateProductService createProductService;

    @Autowired
    DeleteProductService deleteProductService;

    @Autowired
    UpdateProductService updateProductService;

    @GetMapping("/{uid}")
    public ResponseEntity ReadProducts(@PathVariable("uid") int uid)
    {
        ReadProductDto readProductDto = new ReadProductDto(uid);
        return new ResponseEntity(readProductService.readAllProductsByUid(readProductDto), HttpStatus.OK);
    }

    @GetMapping("/{pid}")
    public ResponseEntity ReadProduct(ReadProductService createProductDto,  @PathVariable("pid") int pid)
    {
        ReadProductDto readProductDto = new ReadProductDto(pid);
        return new ResponseEntity(readProductService.readOneProductByPid(readProductDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity CreateProduct(CreateProductDto createProductDto, @RequestPart(value="productImgs", required = false) final List<MultipartFile> productImgs)
    {
        return new ResponseEntity(createProductService.newProduct(createProductDto, productImgs), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity DeleteProduct(DeleteProductDto deleteProductDto)
    {
        return new ResponseEntity(deleteProductService.deleteProduct(deleteProductDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity UpdateProduct(UpdateProductDto updateProductDto, @RequestPart(value="productImgs", required = false) final List<MultipartFile> productImgs)
    {
        return new ResponseEntity(updateProductService.updateProduct(updateProductDto, productImgs), HttpStatus.OK);
    }

}
