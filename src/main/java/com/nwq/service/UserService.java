package com.nwq.service;

import com.nwq.dao.UserDao;
import com.nwq.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @auth nwq
 * @data 2020/6/23
 * @Description
 */


public class UserService {
    private UserDao userDao=new UserDao();

    /*
     * @description 查询
     * @author nwq
     * @date 2020/6/23
     * @params []
     * @return java.util.List<com.nwq.entity.User>
    */
    public List<User> listAll() {
        return userDao.listAll();
    }
    public void addUser(User user) {
        user.setId(null);
        user.setRegisterTime(new Date());
//        user.setDeptId(null);
        userDao.addUser(user);
    }
}
