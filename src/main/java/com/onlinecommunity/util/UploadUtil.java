package com.onlinecommunity.util;


import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {


    public static Result uploadFile(MultipartFile multipartFile, String filePath, String fileType) {
//        to do
        return Result.success();
    }

    public static Result uploadPicture(MultipartFile multipartFile, String filePath) {
        if (multipartFile.isEmpty()) {
            return Result.failure(ResultCode.EMPTY_UPLOAD_FILE);
        }

        if (multipartFile.getSize() > Long.parseLong(MyEnvBeanUtil.getPictureMaxSize()))
            return Result.failure(ResultCode.EXCEED_MAX_PIC_SIZE);

        String fileName = multipartFile.getOriginalFilename();
        Assert.notNull(fileName, "上传文件名为空");
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.') + 1);

//        store to server
        return Result.success();
    }

}
