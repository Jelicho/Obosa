package com.ssafy.obosa.service;


import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.DeleteProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ssafy.obosa.util.ImgHandler.DeleteProductImgs;

@Service
public class DeleteProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    @Value("${uploadpath.product}")
    private String baseDir;

    public DeleteProductService(final ProductRepository productRepository, final FileService fileService, final UserRepository userRepository)
    {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.userRepository = userRepository;
    }
    public DefaultRes<DeleteProductDto> deleteProduct(DeleteProductDto deleteProductDto)
    {
        try
        {
            int uid = deleteProductDto.getUid();
            Optional<User> optuser = userRepository.findByUid(uid);
            if(!optuser.isPresent())
            {
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER);
            }

            User user = optuser.get();

            //삭제할 Product  객체 가져오기
            int pid = deleteProductDto.getPid();
            Optional<Product> optionalProduct = productRepository.findByPid(pid);

            //유효성 검사
            if(!optionalProduct.isPresent()){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_PRODUCT);
            }

            Product product = optionalProduct.get();

            //uid와 product매칭 확인
            //TODO : admin 구현이 완료될시, 관리자에 대한 유효성은 통과히켜주는 조건 포함해야한다.
            if(product.getUser().getUid()!=uid){
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_MATCHED_USER_AND_PRODUCT);
            }

            if(product.getImgCount()>0){
                DeleteProductImgs(fileService, product);
            }

            productRepository.delete(product);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETED_PRODUCT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

}
