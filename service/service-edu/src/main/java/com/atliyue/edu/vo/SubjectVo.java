package com.atliyue.edu.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private List<ChildSubjectVo> childSubject;

}
