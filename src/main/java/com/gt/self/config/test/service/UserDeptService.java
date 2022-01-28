package com.gt.self.config.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author GTsung
 * @date 2022/1/28
 */
@Service
public class UserDeptService {

    @Value("${person.name}")
    String pname;

    public String getName() {
        return pname;
    }
}
