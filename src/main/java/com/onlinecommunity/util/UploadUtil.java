package com.onlinecommunity.util;


import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class UploadUtil {

    final String uploadPath = MyEnvBeanUtil.getProperty("upload.path");

    public static Result uploadFile(MultipartFile multipartFile, String filePath, String fileType) {
//        to do
        return Result.success();
    }

    /**
     * @param multipartFiles 要上传至服务器的图片，已经检验过没有问题(如：超出大小限制)
     * @return 这些图片成功保存后对应的URL列表
     */
    public static List<String> uploadPictures(MultipartFile[] multipartFiles) {


        List<String> urlList = new LinkedList<>();

        //循环保存每一张图片
        for (int i = 0; i < multipartFiles.length; i++) {

            MultipartFile multipartFile = multipartFiles[i];


            //获得原图片的格式
            String originalFilename = multipartFile.getOriginalFilename();
            Assert.notNull(originalFilename, "上传文件名为空");

            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
            log.info(multipartFile.getOriginalFilename());
            log.info("suffix:{}", fileSuffix);

            // generate new unique filename by UUID
            String fileName = UUID.randomUUID() + fileSuffix;
            fileName.replace("-", "");
            log.info("new filename by UUID:{}", fileName);

            // 图片保存后对应的URL
            String saveResult = saveToServer(multipartFile, fileName);
            log.info("picture{},URL: {}", i, saveResult);
            if (saveResult != null) {
                // 保存成功，得到了URL
                urlList.add(saveResult);
            } else {
                // 保存失败
                urlList.add("");
            }
        }
        return urlList;
    }

    /**
     * @param multipartFile 需要保存到服务器的文件
     * @param fileName      经过处理生成的唯一文件名
     * @return 返回该图片对外的访问URL，如果保存失败则返回null
     */
    public static String saveToServer(MultipartFile multipartFile, String fileName) {

        String filePath = MyEnvBeanUtil.getProperty("upload.path");
        log.info("filePath:" + filePath);

        File file = new File(filePath + fileName);
        System.out.println("file.getParentFile() = " + file.getParentFile());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
