package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.ReadProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public DefaultRes<List<Product>> readAllProductsByUid(ReadProductDto readProductDto)
    {
        int uid = readProductDto.getId();
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if(!optionalUser.isPresent())
        {
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER);
        }

        User user = optionalUser.get();
        List<Product> productAllList = productRepository.findByUser(user);
        if(productAllList==null){
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_PRODUCTS);
        }else{
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.READED_ALL_PRODUCTS, productAllList);
        }

    }

    public DefaultRes<Product> readOneProductByPid(ReadProductDto readProductDto)
    {
        int pid = readProductDto.getId();
        Optional<Product> optionalProduct = productRepository.findByPid(pid);
        if(!optionalProduct.isPresent()){
            return DefaultRes.res(StatusCode.OK, ResponseMessage.NOT_FOUND_PRODUCT);
        }
        Product product = optionalProduct.get();
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READED_PRODUCT, product);
    }
}
