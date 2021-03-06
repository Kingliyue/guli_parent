package com.atliyue.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;

import java.util.List;

public class InitVodClientUtil {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-beijing";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
    public static void deleteVod(String AccessKeyId,String  AccessKeySecret,String videoId){
        DefaultAcsClient client = null;
        try {
            client = initVodClient(AccessKeyId, AccessKeySecret);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
             client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
    public static void deleteVod(String AccessKeyId, String  AccessKeySecret, List<String> list
    ){
        DefaultAcsClient client = null;
        try {
            client = initVodClient(AccessKeyId, AccessKeySecret);
            DeleteVideoRequest request = new DeleteVideoRequest();
            if(list.size() > 0 ){
                Object[] objects = list.toArray();

                request.setVideoIds(objects.toString());
            }

            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

}
