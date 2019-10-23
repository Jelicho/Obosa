package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.CreateProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ImgHandler;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    public CreateProductService(final ProductRepository productRepository, final FileService fileService, final UserRepository userRepository)
    {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.userRepository=userRepository;
    }
    public DefaultRes<CreateProductDto> newProduct(CreateProductDto createProductDto, List<MultipartFile> productImgs)
    {
        try
        {
            int uid = createProductDto.getUid();
            Optional<User> optionalUser = userRepository.findByUid(uid);
            if(!optionalUser.isPresent())
            {
                return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER);
            }

            User user = optionalUser.get();
            Product product = Product.setProductByProductDto(createProductDto, user);

            if(productImgs != null)
            {
                ImgHandler.CreateProductImgs(fileService, product, productImgs, uid);
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
