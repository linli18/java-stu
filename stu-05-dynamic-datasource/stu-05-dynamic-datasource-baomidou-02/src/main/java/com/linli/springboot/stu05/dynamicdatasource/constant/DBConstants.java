package com.linli.springboot.stu05.dynamicdatasource.constant;

/**
 * 数据库枚举类
 */
public class DBConstants {

    /**
     * 数据源分组 - 订单库
     */
    public static final String DATASOURCE_ORDER = "orders";

    /**
     * 数据源分组 - 用户库
     */
    public static final String DATASOURCE_USER = "users";

    /**
     * 数据源分组 - 从数据源
     */
    public static final String DATASOURCE_SLAVE = "slave";

    /**
     * 数据源分组 - 主数据源
     */
    public static final String DATASOURCE_MASTER = "master";
}
