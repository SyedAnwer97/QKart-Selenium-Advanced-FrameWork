package com.qkart.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class TestData {

    private String testname;
    private String execute;
    private String username;
    private String password;
    private String invalidusername;
    private String invalidpassword;
    private String product1;
    private String product2;
    private String product3;

}