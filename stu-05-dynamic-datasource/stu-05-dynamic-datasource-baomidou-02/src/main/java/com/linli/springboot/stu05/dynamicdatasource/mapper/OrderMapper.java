package com.linli.springboot.stu05.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.linli.springboot.stu05.dynamicdatasource.constant.DBConstants;
import com.linli.springboot.stu05.dynamicdatasource.dataobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
//@DS(DBConstants.DATASOURCE_ORDER)
public interface OrderMapper {
    @DS(DBConstants.DATASOURCE_SLAVE)
    OrderDO selectById(@Param("id") Integer id);

    @DS(DBConstants.DATASOURCE_MASTER)
    int insert(OrderDO orderDO);
}
