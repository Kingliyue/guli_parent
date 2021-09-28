package com.atliyue.edu.fegin;

import com.atliyue.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(name = "edu-vod",fallback = VodCallback.class)
public interface EduVodClient {
    @DeleteMapping("delete/{videoId}")
    public Result deleteVideo(@PathVariable("videoId") String videoId);
}
