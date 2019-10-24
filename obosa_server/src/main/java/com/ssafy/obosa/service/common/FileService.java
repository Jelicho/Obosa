package com.ssafy.obosa.service.common;

import org.springframework.web.multipart.MultipartFile;

public interface FileService
{
    void fileUpload(MultipartFile multipartFile, String filePath);
    void fileDownload(String filePath);
    void fileDelete(String filePath);
}
