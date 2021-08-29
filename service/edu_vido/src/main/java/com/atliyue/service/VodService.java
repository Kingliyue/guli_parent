package com.atliyue.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String getVideoId(MultipartFile file);
    String deleteVideoById(String videoId);
}
