package com.nwq.dao;

import com.nwq.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth nwq
 * @data 2020/6/23
 * @Description
 */


public class UserDao extends BaseDao{
    //查询  listXXX  findXXX getXXX query getUserById
    //添加  addXXX insertXXX  addUser add
    //修改 update  edit   updateUser
    //删除 delete  remove

    public List<User> listAll() {
        String sql = "select * from user ";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public void addUser(User user) {
        String sql = "INSERT INTO USER ( username, password, email, real_name, age, sex, description, register_time,dept_id )" +
                "values (?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(),
                user.getAge(), user.getSex(), user.getDescription(), user.getRegisterTime(), user.getDeptId());
    }

}
