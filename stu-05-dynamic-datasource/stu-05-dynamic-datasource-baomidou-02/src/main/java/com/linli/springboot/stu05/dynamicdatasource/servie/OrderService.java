package com.linli.springboot.stu05.dynamicdatasource.servie;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.linli.springboot.stu05.dynamicdatasource.constant.DBConstants;
import com.linli.springboot.stu05.dynamicdatasource.dataobject.OrderDO;
import com.linli.springboot.stu05.dynamicdatasource.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @DS(DBConstants.DATASOURCE_MASTER)
    public void add(OrderDO orderDO) {
        orderMapper.selectById(orderDO.getId());

        orderMapper.insert(orderDO);
    }

    public OrderDO selectById(Integer id) {
        return orderMapper.selectById(id);
    }
}
