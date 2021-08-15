package com.linli.springboot.stu05.dynamicdatasource.service;

import com.linli.springboot.stu05.dynamicdatasource.Application;
import com.linli.springboot.stu05.dynamicdatasource.dataobject.OrderDO;
import com.linli.springboot.stu05.dynamicdatasource.servie.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testAdd() {
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(30);
        orderService.add(orderDO);
    }

    @Test
    public void testSelectById() {
        for (int i = 0; i < 10; i++) {
            OrderDO orderDO = orderService.selectById(1);
            System.out.println(orderDO);
        }
    }
}
