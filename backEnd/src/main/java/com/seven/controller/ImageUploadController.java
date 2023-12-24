package com.seven.controller;

import com.seven.domain.dto.MultipartFileDTO;
import com.seven.domain.pojo.Result;
import com.seven.service.StudentService;
import com.seven.utils.ContentTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author :Wjh
 * @since :2023/12/12 10:26
 */

@Slf4j
@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageUploadController {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("classpath:static/img/user");
    @Value("${upload.path.image}")
    private String uploadPath;
    @Autowired
    private StudentService studentService;
    private String targetPath ;

    {
        try {
            targetPath = resource.getFile().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传图片
     * @param studentNumber
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result uploadImage(@RequestBody @RequestParam(required = true) String studentNumber,
                              @RequestParam(value = "file",required = true) MultipartFile file) {
        String tempPath = null;
        String filePath = null;
        String fileName = null;
        try {
            fileName = file.getOriginalFilename();
            // 获取文件名
            // 指定保存路径
            filePath = uploadPath  + "\\" + fileName;
            tempPath = targetPath  + "\\" + fileName;

            log.info("上传文件路径：" + filePath);
            log.info("上传文件路径：" + tempPath);

            file.transferTo(new File(filePath));

            File pdfFile = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(pdfFile);
            MultipartFile targetFile = new MultipartFileDTO(pdfFile.getName(), pdfFile.getName(),
                    ContentTypeUtil.getContentType(fileName), fileInputStream);

            targetFile.transferTo(new File(tempPath));

            String path = "http://localhost:5000/image/user/" + fileName;
            studentService.uploadImage(studentNumber,path);

            // 保存文件到本地
            return Result.success("上传成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("上传失败");
        }
    }
}
