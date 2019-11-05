package com.ssafy.obosa.controller;


import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.CreateProductDto;
import com.ssafy.obosa.model.dto.DeleteProductDto;
import com.ssafy.obosa.model.dto.UpdateProductDto;
import com.ssafy.obosa.service.CreateProductService;
import com.ssafy.obosa.service.DeleteProductService;
import com.ssafy.obosa.service.UpdateProductService;
import com.ssafy.obosa.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/product")
public class ProductController
{
    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final UpdateProductService updateProductService;
    private final UserService userService;
    ProductController(final CreateProductService createProductService, final DeleteProductService deleteProductService,
                      final UpdateProductService updateProductService, final UserService userService){
        this.createProductService=createProductService;
        this.deleteProductService=deleteProductService;
        this.updateProductService=updateProductService;
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestHeader(value = "Authorization", required = false) String jwtToken, CreateProductDto createProductDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(createProductService.newProduct(user, createProductDto), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestHeader(value = "Authorization", required = false) String jwtToken, DeleteProductDto deleteProductDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(deleteProductService.deleteProduct(user, deleteProductDto), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestHeader(value = "Authorization", required = false) String jwtToken, UpdateProductDto updateProductDto)
    {
        try{
            User user = userService.getUserByJwtToken(jwtToken);
            if(user == null)
            {
                return new ResponseEntity<>(DefaultRes.UNAUTHORIZATION, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(updateProductService.updateProduct(user, updateProductDto), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
