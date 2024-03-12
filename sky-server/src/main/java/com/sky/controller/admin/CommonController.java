package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.properties.AliOssProperties;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
*/
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * 文件上传
     **/

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}",file);
        //原始文件名
        try {
            String orginalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension = orginalFilename.substring(orginalFilename.lastIndexOf("."));
            //构造新文件名称
            String ObjectName = UUID.randomUUID().toString()+ extension;

            //文件的请求路径
            String filepath = aliOssUtil.upload(file.getBytes(), ObjectName);
            return Result.success(filepath);
        } catch (IOException e) {
            log.info("文件传输失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
