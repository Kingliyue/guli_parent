package com.atliyue.service.imp;

import com.atliyue.exception.MyException;
import com.atliyue.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MsmServiceImp implements MsmService {

    @Override
    public Boolean send(String iphone,String code) {
        if (StringUtils.isEmpty(iphone)){
            throw new MyException(20001,"手机号不能为空");
        }

       // String code = RandomUtil.getFourBitRandom();

        //todo 这里写阿里云短息功能


        return true;
    }
}
