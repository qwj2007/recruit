package com.recruit.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者：qiwj
 * 时间：2020/8/1
 */
@RestController
public class TestController {
    @RequestMapping("test/login")
    public String login()
    {
        return "login";
    }


}
