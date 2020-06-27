package com.nwq.dao;

import com.nwq.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auth nwq
 * @date 2020/6/22
 * @Description
 */
public class BaseDao {

    public JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

}
