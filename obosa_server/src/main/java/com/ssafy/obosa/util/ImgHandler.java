package com.ssafy.obosa.util;

import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.service.common.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
public class ImgHandler {

    private static String ProductsDir;

    @Value("${uploadpath.product}")
    private void setProductsDir(String product) {
        ProductsDir = product;
    }

    public static void createProductImgs(FileService fileService, Product product, List<MultipartFile> productImgs, int uid) {
        int imgCount = productImgs.size();
        int forFilename_count = 1;
        String dirS3=UUID.randomUUID().toString();

        for (MultipartFile productImg : productImgs) {
            String filePath = new StringBuilder(ProductsDir)
                    .append(uid)
                    .append('/')
                    .append(dirS3)
                    .append('/')
                    .append(forFilename_count++).toString();
            System.out.println(filePath);
            fileService.fileUpload(productImg, filePath);
        }
        product.setDirS3(dirS3);
        product.setImgCount(imgCount);
    }

    public static void createProductImg(FileService fileService, Product product, MultipartFile productImg, int uid) {
        int imgCount = 1;
        String dirS3=UUID.randomUUID().toString();

        String filePath = new StringBuilder(ProductsDir)
                .append(uid)
                .append('/')
                .append(dirS3)
                .append('/')
                .append(1).toString();
        System.out.println(filePath);
        fileService.fileUpload(productImg, filePath);

        product.setDirS3(dirS3);
        product.setImgCount(imgCount);
    }
    public static void deleteProductImgs(FileService fileService, Product product) {
        int imgCount = product.getImgCount();
        int forFilename_count = 1;
        while (forFilename_count <= imgCount) {
            String filePath = new StringBuilder(ProductsDir)
                    .append(product.getUser().getUid())
                    .append('/')
                    .append(product.getDirS3())
                    .append('/')
                    .append(forFilename_count++).toString();
            fileService.fileDelete(filePath);
        }
    }

    public static void updateProductImgs(FileService fileService, Product product, List<MultipartFile> productImgs) {

        if(productImgs==null){
            deleteProductImgs(fileService, product);
            product.setImgCount(0);
        }
        else{
            int before_imgCount = product.getImgCount();
            int after_imgCount = productImgs.size();
            while (after_imgCount <= before_imgCount) {
                String filePath = new StringBuilder(ProductsDir)
                        .append(product.getUser().getUid())
                        .append('/')
                        .append(product.getDirS3())
                        .append('/')
                        .append(after_imgCount++).toString();
                fileService.fileDelete(filePath);
            }
            int forFilename_count=1;
            if(productImgs!=null){

            }
            for (MultipartFile productImg : productImgs) {
                String filePath = new StringBuilder(ProductsDir)
                        .append(product.getUser().getUid())
                        .append('/')
                        .append(product.getDirS3())
                        .append('/')
                        .append(forFilename_count++).toString();
                System.out.println(filePath);
                fileService.fileUpload(productImg, filePath);
            }
            product.setImgCount(productImgs.size());
        }

    }
}