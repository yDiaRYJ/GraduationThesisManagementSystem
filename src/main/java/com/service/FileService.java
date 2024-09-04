package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class FileService {
    @Value("${upload.dir}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    // 获取文件后缀名
    private static String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // 没有后缀名
        }
        return fileName.substring(lastIndexOfDot + 1); // 获取后缀名
    }

    public static String getDocumentTypeName(int docType) {
        switch (docType) {
            case 1:
                return "开题报告";
            case 2:
                return "中期文档";
            case 3:
                return "论文初稿";
            case 4:
                return "检测版论文";
            case 5:
                return "论文终稿";
            default:
                return "未知文档类型";
        }
    }

    public ResponseEntity<FileSystemResource> getFile(String filePath) {
        File file = new File(filePath);

        // 检查文件是否存在
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName;
        try {
            encodedFileName = URLEncoder.encode(file.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 处理异常
            encodedFileName = file.getName(); // 或者设置一个默认文件名
        }
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");

        // 返回文件资源
        FileSystemResource resource = new FileSystemResource(file);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


    public String saveFile(MultipartFile file, String studentId, int docType) {
        // 获取文件后缀
        String oriFileName = file.getOriginalFilename(); // 获取文件名
        String fileSuffix = getFileExtension(oriFileName);
        // 创建文件路径
        String filePath = uploadDir + File.separator + studentId + File.separator + getDocumentTypeName(docType) + '.' + fileSuffix;
        // 创建文件
        File targetFile = new File(filePath);
        // 保存文件
        try {
            // 确保目录存在
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs(); // 创建目录
            }
            // 保存文件
            file.transferTo(targetFile);
            return studentId + File.separator + getDocumentTypeName(docType) + '.' + fileSuffix;
        } catch (IOException e) {
            return null;
        }
    }
}
