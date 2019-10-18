package com.ssafy.obosa.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class S3Util
{
    public static void displayText(InputStream input) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while(true)
        {
            String line = reader.readLine();
            if(line == null) break;
            System.out.println("    " + line);
        }
    }

    public static String getFilePath(String baseDir, MultipartFile file)
    {
        return new StringBuilder(baseDir)
                .append(S3Util.getUuid())
                .append(file.getOriginalFilename()).toString();
    }

    public static String getUuid()
    {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getImgPath(String endPoint, String imgPath)
    {
        return new StringBuilder(endPoint).append(imgPath).toString();
    }
}
