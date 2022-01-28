package com.gt.self.config.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author GTsung
 * @date 2022/1/28
 */
@Service
public class UserService {

    @Value("${person.name}")
    String name;

    @Value("${person.age}")
    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
