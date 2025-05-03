package com.neoteric.innerjoin.polymorphysmforresponse;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Subjects {

    private String subject;
    private int marks;

    public Subjects(){

    }

    public Subjects(String studentId, String subject, int marks) {
        this.subject = subject;
        this.marks = marks;
    }
}
