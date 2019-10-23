package com.ssafy.obosa.service.common;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.ssafy.obosa.util.S3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class S3FileServiceImpl implements FileService
{
    @Autowired
    private AmazonS3 s3Client;

    @Value("${cloud.aws.bucket}")
    private String bucketName;

    @Async
    @Override
    public void fileUpload(MultipartFile multipartFile, String filePath)
    {
        try
        {
            String dirName = filePath.substring(0, filePath.lastIndexOf("/"));

            File f = new File(filePath);
            if(!f.getParentFile().exists())
            {
                f.getParentFile().mkdirs();
            }
            if(!f.exists())
            {
                f.createNewFile();
            }

            File convFile = new File(multipartFile.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();
            s3Client.putObject(new PutObjectRequest(bucketName, filePath, convFile));
            convFile.delete();
        }
        catch(AmazonServiceException ase)
        {
            ase.printStackTrace();
        }
        catch(AmazonClientException ace)
        {
            ace.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void fileDownload(String filePath)
    {
        try
        {
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, filePath));
            S3Util.displayText(s3Object.getObjectContent());
        }
        catch(AmazonServiceException ase)
        {
            ase.printStackTrace();
        }
        catch(AmazonClientException ace)
        {
            ace.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void fileDelete(String filePath)
    {
        try
        {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, filePath));
        }
        catch(AmazonServiceException ase)
        {
            ase.printStackTrace();
        }
        catch(AmazonClientException ace)
        {
            ace.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
