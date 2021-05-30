package com.atliyue.oss.upload.service;

import org.springframework.web.multipart.MultipartFile;


public interface OssUploadService {
    /**
     * 分段上传文件
     * @param file
     */
    void ossUpload(MultipartFile file);

    /**
     * 上传头像
     * @param file
     */
    String  ossUploadFile(MultipartFile file);
    /**
     * 上传文件
     */
    String ossUploadExcel(MultipartFile file);
}
