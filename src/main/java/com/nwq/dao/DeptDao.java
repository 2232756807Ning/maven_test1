package com.nwq.dao;

import com.nwq.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth nwq
 * @data 2020/6/23
 * @Description
 */


public class DeptDao extends BaseDao{
    public List<Dept> listAll() {
        String sql = "select * from dept";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

}
