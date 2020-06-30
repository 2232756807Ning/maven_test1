package com.nwq.dao;

import com.nwq.entity.Page;
import com.nwq.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth nwq
 * @data 2020/6/23
 * @Description
 */


public class UserDao extends BaseDao {
    //查询  listXXX  findXXX getXXX query getUserById
    //添加  addXXX insertXXX  addUser add
    //修改 update  edit   updateUser
    //删除 delete  remove

    public List<User> listAll(String username, Page page) {
        String sql = "SELECT " +
                " d.name deptName, " +
                " u.id id, " +
                " u.dept_id deptId, " +
                " u.username username, " +
                " u.email email, " +
                " u.real_name realName, " +
                " u.age age, " +
                " u.sex sex, " +

                "case when sex=1 then \"男\" " +
                " when sex=0 then \"女\" " +
                " else \"其他\" end sexName," +

                " u.description description, " +
                " u.register_time registerTime  " +
                "FROM " +
                " USER u " +
                " LEFT JOIN dept d ON u.dept_id = d.id  " +
                "WHERE " +
                " username LIKE ?  LIMIT ?,?";

        return template.query(sql, new BeanPropertyRowMapper<>(User.class),
                "%" + username + "%", (page.getPageCurrent() - 1) * page.getSize(), page.getSize());
    }

    //    public List<User> listAll(String name,String sex) {
//        if ("-1".equals(sex)){
//            String sql = "select * from user where username like ? ";
//            return template.query(sql, new BeanPropertyRowMapper<>(User.class),
//                    "%"+name+"%");
//        } else{
//            String sql = "select * from user where username like ? and sex = ?  ";
//            return template.query(sql, new BeanPropertyRowMapper<>(User.class),
//                    "%"+name+"%",Integer.valueOf(sex));
//        }
//    }
    public Integer getCount(String username) {
        String sql = "select count(*) from user where username like ?";
        try {
            return template.queryForObject(sql, Integer.class, "%" + username + "%");
        } catch (DataAccessException e) {
            return 0;
        }
//        String sql = "select count(*) from user ";
////        String sql = "select count(*) from user where username like ?";
//        try {
//            return template.queryForObject(sql, Integer.class);
////            return template.queryForObject(sql, Integer.class, "%" + username + "%");
//        } catch (DataAccessException e) {
//            return 0;
//        }
    }

    public void addUser(User user) {
        String sql = "INSERT INTO USER ( username, password, email, real_name, age, sex, description, register_time,dept_id )" +
                "values (?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(),
                user.getAge(), user.getSex(), user.getDescription(), user.getRegisterTime(), user.getDeptId());
    }

    public User getUserByUserName(String userName) {
        String sql = "select * from user where username=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public User getUserById(Integer id) {
        String sql = "select * from user where id=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }


    public User update(String name, Integer id) {
        String sql = "update user set username=?  where id= ?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    public User checkLogin(User user) {
        //此系统账号必须唯一
        String sql = "select * from user where username=? and password=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void updatePs(String username, String newPs) {
        String sql = "update user set password=? where username=? ";
        template.update(sql, newPs, username);
    }
    public void updatePic(Integer id, String pic) {
        String sql = "update user set pic=? where id=? ";
        template.update(sql, pic, id);
    }

}
