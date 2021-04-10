package com.liyue.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class  Reselt {
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
    private Reselt(){
    }
    //成功
    public static Reselt ok(){
        Reselt reselt = new Reselt();
        reselt.setCode(ResultCode.sucese);
        reselt.setMessage("操作成功");
        reselt.setSuccess(true);
        return reselt;
    }
    //失败
    public static Reselt error(){
        Reselt reselt = new Reselt();
        reselt.setCode(ResultCode.error);
        reselt.setMessage("操作失败");
        reselt.setSuccess(false);
        return reselt;
    }
    //链式编程
    public  Reselt code(Integer code){
        this.setCode(code) ;
        return this;
    }
    public Reselt success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public Reselt message(String message){
        this.setMessage(message);
        return this;
    }
    public  Reselt data(HashMap<String,Object> data){
        this.setData(data);
        return this;
    }
    public  Reselt data(String key, Object value){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(key,value);
        this.setData(hashMap);
        return this;
    }



}
