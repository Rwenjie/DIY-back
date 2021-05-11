package nuc.rwenjie.common.utils.ali.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Rwenjie
 * @ClassName aliOSSFiles
 * @Description TODO OSS 文件上传
 * @Date 2021/4/26 19:18
 **/

@Service
public class AliOSSFiles {

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.endpoint}")
    private String endpoint;

    public URL uploadFile(String username, String filetype, MultipartFile multipartFile) throws OSSException, ClientException, FileNotFoundException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        URL url = null;
        String fileurl ="diy-shop/" + username+'/'+filetype+'/';

        // 获取文件的后缀名
        String oldName = multipartFile.getOriginalFilename();
        String suffixName = oldName.substring(oldName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = fileurl +sdf.format(new Date()) + finalFileName;
        System.out.println(objectName);
        // 创建OSSClient的实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if(multipartFile.getSize() != 0 && !"".equals(multipartFile.getName())){
                // getInputStream()返回一个InputStream以从中读取文件的内容。通过此方法就可以获取到流
                InputStream multipartFileInputStream = multipartFile.getInputStream();
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, multipartFileInputStream);
                ossClient.putObject(putObjectRequest);
            }
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            System.out.println(url);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭流
            ossClient.shutdown();
        }
        return url;
    }

}
