package com.atliyue.edu.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class EduLoginVo implements Serializable {
    private String username;
    private String password;
}
