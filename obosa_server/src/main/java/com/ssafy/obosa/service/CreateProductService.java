package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.dto.CreateProductDto;
import com.ssafy.obosa.repository.ProductRepository;
import com.ssafy.obosa.service.common.FileService;
import com.ssafy.obosa.util.ImgHandler;
import com.ssafy.obosa.util.ResponseMessage;
import com.ssafy.obosa.util.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;

    public CreateProductService(final ProductRepository productRepository, final FileService fileService)
    {
        this.productRepository = productRepository;
        this.fileService = fileService;
    }
    public DefaultRes<CreateProductDto> newProduct(User user, CreateProductDto createProductDto)
    {
        try
        {
            Product product = Product.setProductByProductDto(createProductDto, user);
            List<MultipartFile> productImgs = createProductDto.getProductImgs();
            if(productImgs != null)
            {
                ImgHandler.createProductImgs(fileService, product, productImgs, user.getUid());
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
