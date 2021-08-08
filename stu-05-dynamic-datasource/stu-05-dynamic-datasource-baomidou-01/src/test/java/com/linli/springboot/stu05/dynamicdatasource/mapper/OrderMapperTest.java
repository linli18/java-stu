package com.linli.springboot.stu05.dynamicdatasource.mapper;

import com.linli.springboot.stu05.dynamicdatasource.Application;
import com.linli.springboot.stu05.dynamicdatasource.dataobject.OrderDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSelectById() {
        OrderDO orderDO = orderMapper.selectById(1);
        System.out.println(orderDO);
    }
}
