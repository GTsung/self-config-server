package com.gt.self.config.test.controller;

import com.gt.self.config.test.service.UserDeptService;
import com.gt.self.config.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GTsung
 * @date 2022/1/28
 */
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    UserDeptService userDeptService;

    @GetMapping("name")
    public String getUserName() {
        return " name: " + userService.getName() + "\r\n age:  " + userService.getAge();
    }

    @GetMapping("name2")
    public String getUserDeptName() {
        return "name: " + userDeptService.getName();
    }

}
