package com.mkyong.web.controller;

import com.lgcns.erp.tapps.DbContext.UserService;

public class HelloController {

    public static void main(String[] args) {
        System.out.println(UserService.getAllUserLocs());
    }
}