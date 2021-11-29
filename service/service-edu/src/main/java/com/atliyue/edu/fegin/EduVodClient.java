package com.atliyue.edu.fegin;

import com.atliyue.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient(name = "edu-vod",fallback = VodCallback.class)
@RequestMapping("edu/vod")
public interface EduVodClient {
    @DeleteMapping("delete/{videoSourceId}")
    public Result deleteVideo(@PathVariable("videoSourceId") String videoSourceId);
    //批量删除
    @DeleteMapping("delete")
    public Result deleteVideo(@RequestBody List<String> list);
}
