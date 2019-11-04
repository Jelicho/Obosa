package com.ssafy.obosa.model.dto;

import com.ssafy.obosa.model.domain.Product;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@Builder
@ToString
public class ProductDto {
    private int pid;
    private String pname;
    private String pdescription;
    private int imgCount;
    private String dirS3;
    private UserDto user;
    public static ProductDto setProductDtoByProduct(Product product)
    {
        return ProductDto.builder()
                .pname(product.getPname())
                .pdescription(product.getPdescription())
                .imgCount(product.getImgCount())
                .dirS3(product.getDirS3())
                .user(UserDto.setUserDtoByUser(product.getUser()))
                .build();
    }
    public static Page<ProductDto> setProductDtoListByProductList(Page<Product> products, Pageable pageable)
    {
        List<ProductDto> productDtosList = null;
        for(Product product:products){
            productDtosList.add(setProductDtoByProduct(product));
        }
        Page<ProductDto> productDtosPage = new PageImpl<>(productDtosList, pageable, productDtosList.size());
        return productDtosPage;
    }
}