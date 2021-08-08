package com.linli.springboot.stu05.dynamicdatasource.mapper;

import com.linli.springboot.stu05.dynamicdatasource.Application;
import com.linli.springboot.stu05.dynamicdatasource.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectOne() {
        UserDO userDO = userMapper.selectById(1);
        System.out.println(userDO);
    }
}
