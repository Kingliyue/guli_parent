package com.atliyue.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class  Result {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 构造函数私有化
       阻止外部实例化
      */
    private Result(){
    }
    //成功
    public static Result ok(){
        Result reselt = new Result();
        reselt.setCode(ResultCode.sucese);
        reselt.setMessage("操作成功");
        reselt.setSuccess(true);
        return reselt;
    }
    //失败
    public static Result error(){
        Result reselt = new Result();
        reselt.setCode(ResultCode.error);
        reselt.setMessage("操作失败");
        reselt.setSuccess(false);
        return reselt;
    }
    //链式编程
    public  Result code(Integer code){
        this.setCode(code) ;
        return this;
    }
    public Result success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public  Result data(Map<String,Object> data){
        this.setData(data);
        return this;
    }
    private static Map<String, Object> hashMap = new HashMap<>();
    public  Result data(String key, Object value){
        hashMap.put(key,value);
        this.setData(hashMap);
        return this;
    }



}
