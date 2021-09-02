package com.atliyue.service.Imp;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atliyue.service.VodService;
import com.atliyue.utils.ConstantVodUtil;
import com.atliyue.utils.InitVodClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VodServiceImp implements VodService {

    @Override
    public String getVideoId(MultipartFile file) {
        String  accessKeyId = ConstantVodUtil.accessKey_id;
        String  accessKeySecret = ConstantVodUtil.accessKey_Secret;
        String  fileName = file.getOriginalFilename();
        String  title = fileName.substring(0, fileName.lastIndexOf("."));


        UploadStreamRequest request = null;
        try {
            request = new UploadStreamRequest(accessKeyId, accessKeySecret, title,fileName, file.getInputStream());
            request.setStorageLocation("outin-857fc87308cb11ec87ae00163e038793.oss-cn-beijing.aliyuncs.com");
            request.setApiRegionId("cn-beijing");
        } catch (IOException e) {
            e.printStackTrace();
        }
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return  response.getVideoId();
    }

    @Override
    public String deleteVideoById(String videoId) {
        InitVodClientUtil.deleteVod(ConstantVodUtil.accessKey_id,ConstantVodUtil.accessKey_Secret,videoId);
        return null;
    }
}
