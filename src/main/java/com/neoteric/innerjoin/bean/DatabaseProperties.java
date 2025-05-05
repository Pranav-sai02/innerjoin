package com.neoteric.innerjoin.bean;


import lombok.Getter;

@Getter
public class DatabaseProperties {
    private String url = "jdbc:mysql://127.0.0.1:3306/studentdb";
    private String username = "root";
    private String password = "Count02";
    private String driverClassName = "com.mysql.cj.jdbc.Driver";

}

