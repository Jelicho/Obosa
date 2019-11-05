package com.ssafy.obosa.controller;


import com.ssafy.obosa.model.dto.CreateProductDto;
import com.ssafy.obosa.model.dto.DeleteProductDto;
import com.ssafy.obosa.model.dto.UpdateProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.CreateProductService;
import com.ssafy.obosa.service.DeleteProductService;
import com.ssafy.obosa.service.UpdateProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("api/product")
public class ProductController
{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final UpdateProductService updateProductService;
    ProductController(final UserRepository userRepository, final ProductRepository productRepository,
                      final CreateProductService createProductService, final DeleteProductService deleteProductService,
                      final UpdateProductService updateProductService){
        this.userRepository=userRepository;
        this.productRepository=productRepository;
        this.createProductService=createProductService;
        this.deleteProductService=deleteProductService;
        this.updateProductService=updateProductService;
    }
//    @Autowired
//    ReadProductService readProductService;
//    @GetMapping("/{uid}")
//    public ResponseEntity readProducts(@PathVariable("uid") int uid)
//    {
//        ReadProductDto readProductDto = new ReadProductDto(uid);
//        return new ResponseEntity(readProductService.readAllProductsByUid(readProductDto), HttpStatus.OK);
//    }
//
//    @GetMapping("/{pid}")
//    public ResponseEntity readProduct(@PathVariable("pid") int pid)
//    {
//        ReadProductDto readProductDto = new ReadProductDto(pid);
//        return new ResponseEntity(readProductService.readOneProductByPid(readProductDto), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity createProduct(CreateProductDto createProductDto, @RequestPart(value="productImgs", required = false) final List<MultipartFile> productImgs)
    {
        return new ResponseEntity(createProductService.newProduct(createProductDto, productImgs), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(DeleteProductDto deleteProductDto)
    {
        return new ResponseEntity(deleteProductService.deleteProduct(deleteProductDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateProduct(UpdateProductDto updateProductDto, @RequestPart(value="productImgs", required = false) final List<MultipartFile> productImgs)
    {
        return new ResponseEntity(updateProductService.updateProduct(updateProductDto, productImgs), HttpStatus.OK);
    }

}
