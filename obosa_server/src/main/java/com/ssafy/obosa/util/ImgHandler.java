package com.ssafy.obosa.util;

import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.service.common.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImgHandler {

    private static String ProductsDir;

    @Value("${uploadpath.product}")
    private void setProductsDir(String product) {
        ProductsDir = product;
    }

    //FIXME : S3에 어떻게 ProductFile들을 구분할지 정해지면 수정할 예정.
    public static void CreateProductImgs(FileService fileService, Product product, List<MultipartFile> productImgs) {
        int imgCount = productImgs.size();
        int forFilename_count = 1;
        for (MultipartFile productImg : productImgs) {
            System.out.println(ProductsDir);
            String filePath = new StringBuilder(ProductsDir)
                    .append(product.getPid())
                    .append('/')
                    .append(forFilename_count++).toString();
            fileService.fileUpload(productImg, filePath);
        }
        product.setImgCount(imgCount);
    }

    public static void DeleteProductImgs(FileService fileService, Product product) {
        int imgCount = product.getImgCount();
        int forFilename_count = 1;
        while (forFilename_count < imgCount) {
            String filePath = new StringBuilder(ProductsDir)
                    .append(product.getPid())
                    .append('/')
                    .append(forFilename_count++).toString();
            fileService.fileDelete(filePath);
        }
    }
}