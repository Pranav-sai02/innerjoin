package com.neoteric.innerjoin.bean;

import com.neoteric.innerjoin.polymorphysmforresponse.Subjects;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Students {

    private String studentName;
    private int studentAge;
    private String studentId;
    private List<Subjects> subjects;

    public Students(){

    }

}
