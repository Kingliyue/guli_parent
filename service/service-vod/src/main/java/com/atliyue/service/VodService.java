package com.atliyue.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String getVideoId(MultipartFile file);

    String deleteVideoById(String videoId);

    void deleteVideo(List<String> list);
}
