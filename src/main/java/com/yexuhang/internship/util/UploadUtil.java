package com.yexuhang.internship.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Xuhang Ye
 * @time 4:26 PM
 */
public class UploadUtil {
    // 阿里域名
    public static final String ALI_DOMAIN = "https://24-summer-internship.oss-cn-chengdu.aliyuncs.com/";

    public static String uploadImage(MultipartFile file) throws IOException {
        //生成文件名
        String originalFilename = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;

        String endpoint = "oss-cn-chengdu.aliyuncs.com";
        String accessKeyId = "LTAI5tMo9XGsgJepGLCzvK1P";
        String accessKeySecret = "SjzqHoA4bljiv0sMRdnddLDbjvOGAp";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(
                "24-summer-internship",
                "images/" + fileName,
                file.getInputStream()
        );
        // 关闭OSSClient。
        ossClient.shutdown();
        return ALI_DOMAIN + "images/" + fileName;
    }
}
