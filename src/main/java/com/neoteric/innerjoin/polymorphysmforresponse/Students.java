package com.neoteric.innerjoin.polymorphysmforresponse;

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

    public Students(String studentName, int studentAge, String studentId, List<Subjects> subjects) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentId = studentId;
        this.subjects = subjects;
    }
}
