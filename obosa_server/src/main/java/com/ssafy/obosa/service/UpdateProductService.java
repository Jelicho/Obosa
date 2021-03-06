package com.ssafy.obosa.service;


import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.UpdateProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ImgHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    @Value("${uploadpath.product}")
    private String baseDir;

    public UpdateProductService(final ProductRepository productRepository, final FileService fileService, final UserRepository userRepository)
    {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.userRepository=userRepository;
    }
    public DefaultRes<UpdateProductDto> updateProduct(User user, UpdateProductDto updateProductDto)
    {
        try
        {
            //삭제할 Product  객체 가져오기
            int pid = updateProductDto.getPid();
            Optional<Product> optionalProduct = productRepository.findByPid(pid);

            //유효성 검사
            if(!optionalProduct.isPresent()){
                return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_PRODUCT);
            }

            Product product = optionalProduct.get();
            if(product.getUser().getUid()!=user.getUid()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_PERMISSION_ACCESS);
            }
            product.setPname(updateProductDto.getPname());
            product.setPdescription(updateProductDto.getPdescription());

            List<MultipartFile> productImgs = updateProductDto.getProductImgs();
            ImgHandler.updateProductImgs(fileService, product, productImgs);
            productRepository.save(product);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_PRODUCT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

}
