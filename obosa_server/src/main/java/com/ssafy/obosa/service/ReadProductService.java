package com.ssafy.obosa.service;

import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.ProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    public ReadProductService(final ProductRepository productRepository, final FileService fileService, final UserRepository userRepository)
    {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    public DefaultRes<Page<ProductDto>> readAllProducts(User user, Pageable pageable)
    {
        Page<Product> productAllList = productRepository.findByUser(user, pageable);
        if(productAllList==null){
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_PRODUCTS);
        }else{
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_ALL_PRODUCTS, ProductDto.setProductDtoListByProductList(productAllList, pageable));
        }
    }

    public DefaultRes<ProductDto> readOneProductByPid(User user, int pid)
    {
        Optional<Product> optionalProduct = productRepository.findByPid(pid);
        if(!optionalProduct.isPresent()){
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_PRODUCT);
        }
        Product product = optionalProduct.get();
        if(product.getUser()!=user){
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
        }
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_PRODUCT, ProductDto.setProductDtoByProduct(product));
    }
}
