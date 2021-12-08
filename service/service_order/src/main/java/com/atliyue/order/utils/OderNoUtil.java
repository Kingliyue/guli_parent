package com.atliyue.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OderNoUtil {

    public static String getOrderNo(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = dateFormat.format(new Date());
        Random random = new Random();
        for(int i =0 ;i< 3 ;i++){
            format+= random.nextInt(10);
        }
        return format;
    }
}
