package com.linli.springboot.stu04.resourceserverdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Callback02Controller {
    @GetMapping("/callback02")
    public String callback02() {
        return "模拟页面";
    }
}
