package com.linli.springboot.stu05.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.linli.springboot.stu05.dynamicdatasource.constant.DBConstants;
import com.linli.springboot.stu05.dynamicdatasource.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@DS(DBConstants.DATASOURCE_USER)
public interface UserMapper {
    UserDO selectById(@Param("id") Integer id);
}
