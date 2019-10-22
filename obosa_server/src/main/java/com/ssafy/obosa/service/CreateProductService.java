package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.ProductRegistrationDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.S3Util;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRegistrationService {

    private final ProductRepository productRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    @Value("${uploadpath.product}")
    private String baseDir;

    public ProductRegistrationService(final ProductRepository productRepository, final FileService fileService, final UserRepository userRepository)
    {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.userRepository=userRepository;
    }
    public DefaultRes<ProductRegistrationDto> newProduct(ProductRegistrationDto productRegistrationDto, List<MultipartFile> productImgs)
    {
        try
        {
            int uid = productRegistrationDto.getUid();
            Optional<User> optuser = userRepository.findByUid(uid);
            if(!optuser.isPresent())
            {
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER);
            }

            User user = optuser.get();
            Product product = Product.setProductByProductRegistrationDto(productRegistrationDto, user);


            //FIXME : S3에 어떻게 ProductFile들을 구분할지 정해지면 수정할 예정.
            if(productImgs != null)
            {
                int imgCount = productImgs.size();
                for(MultipartFile productImg : productImgs){
                    String filePath = new StringBuilder(baseDir)
                            .append(S3Util.getUuid())
                            .append(productImg.getOriginalFilename()).toString();
                    fileService.fileUpload(productImg, filePath);
                }

                product.setImgCount(imgCount);
            }
            //else => default는 0로 설정되어 있다.

            productRepository.save(product);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_PRODUCT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

}
