package com.bookManage.controller;

import com.bookManage.constant.ImageFileConstant;
import com.bookManage.domain.dto.MultipartFileDTO;
import com.bookManage.domain.pojo.Result;
import com.bookManage.service.StudentService;
import com.bookManage.utils.ContentTypeUtil;
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

@Slf4j
@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageUploadController {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource(ImageFileConstant.IMAGE_PATH);
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

            String path = ImageFileConstant.IMAGE_URL + fileName;
            studentService.uploadImage(studentNumber,path);

            // 保存文件到本地
            return Result.success(ImageFileConstant.UPLOAD_SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(ImageFileConstant.UPLOAD_FAILED+e.getMessage());
        }
    }
}
