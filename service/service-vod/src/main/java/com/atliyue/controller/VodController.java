package com.atliyue.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atliyue.exception.MyException;
import com.atliyue.result.Result;
import com.atliyue.service.VodService;
import com.atliyue.utils.ConstantVodUtil;
import com.atliyue.utils.InitVodClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("edu/vod")
@CrossOrigin
public class VodController {
    /**
     * 上传视频
     * @param file
     * @return
     */
    @Autowired
    private VodService vodService;
    @PostMapping("videoUpload")
    public Result videoUpload(MultipartFile file){
        String videoId = vodService.getVideoId(file);
        return Result.ok().data("videoId",videoId);
    }
    @DeleteMapping("delete/{videoSourceId}")
    public Result deleteVideo(@PathVariable String videoSourceId){
        vodService.deleteVideoById(videoSourceId);
        return Result.ok();
    }
    //获取权限id
    @GetMapping("getPlayAuth/{id}")
    public Result getAuthPlay(@PathVariable("id") String id){
        try {
            DefaultAcsClient client = InitVodClientUtil.initVodClient(ConstantVodUtil.accessKey_id, ConstantVodUtil.accessKey_Secret);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
            String playAuth = acsResponse.getPlayAuth();
            return Result.ok().data("playAuth",playAuth);
        }catch (Exception e){
            throw  new MyException(20001,e.getMessage());
        }
    }
}
