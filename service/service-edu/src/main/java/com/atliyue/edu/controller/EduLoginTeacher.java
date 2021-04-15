package com.atliyue.edu.controller;

import com.atliyue.edu.service.TeacherService;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("edu/teacher")
@CrossOrigin//跨域
public class EduLoginTeacher {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("user/login")
    public Result login(@RequestParam("username")String username,
                        @RequestParam("password")String password){
        return Result.ok().data("token","admin");
    }
    @PostMapping("user/info")
    public Result getInfo(@RequestParam("token") String token){


        return Result.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=2&pn=5&spn=0&di=181390&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=316865976%2C2940650346&os=461721405%2C2504452565&simid=3338155101%2C3954039504&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1104%2F11%2Fc2%2F7278725_7278725_1302501879093_mthumb.jpg%26refer%3Dhttp%3A%2F%2Fimg.pconline.com.cn%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1621078738%26t%3Dde01e7a4bf428e93a0ffae822accb557&fromurl=ippr_z2C%24qAzdH3FAzdH3F1r_z%26e3Brv5gstgj_z%26e3Bv54_z%26e3BvgAzdH3Fri5p5AzdH3Fdamlbmn_z%26e3Bip4s&gsm=6&islist=&querylist=");

    }
}
