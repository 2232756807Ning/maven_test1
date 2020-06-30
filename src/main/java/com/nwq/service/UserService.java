package com.nwq.service;

import com.nwq.dao.UserDao;
import com.nwq.entity.Page;
import com.nwq.entity.User;

import java.util.Date;
import java.util.List;

import com.nwq.utils.MdUtil;
import org.springframework.util.StringUtils;

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
    public Page<User> listAll(String name, String pageStr) {
        // 选中 shift+F6 （替换/改名）
        Page page = new Page<User>();

        //当前页
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPageCurrent(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(userDao.getCount(name));
        //总数据
        List<User> list = userDao.listAll(name, page);
        page.setData(list);
        return page;

    }
//    public List<User> listAll(String name, Page page) {
//        return userDao.listAll(name,page);
//    }
//    public List<User> listAll(String name,String sex) {
//        return userDao.listAll(name,sex);
//    }

//    public Integer getCount() {
//        return userDao.getCount();
//    }
    public void addUser(User user) {
//        user.setId(null);
//        user.setPassword(MdUtil.md5(user.getPassword()));
//        user.setRegisterTime(new Date());
//        userDao.addUser(user);
        user.setId(null);
        user.setRegisterTime(new Date());
//        user.setDeptId(null);
        userDao.addUser(user);
    }
    /*
     * @description 验证用户名是否存在
     * @author nwq
     * @date 2020/6/23
     * @params [userName]
     * @return boolean
    */
    public boolean getUserByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        if (user == null) {
            return true;
        }
        //账号已存在
        return false;
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }



    public void update(User user) {
        user.setRegisterTime(new Date());
        userDao.update(user.getUsername(),user.getId());

    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    /**
     * @Author nwq
     * @Version  1.0
     * @Description 验证账号和密码
     * @Date 2020/6/29 18:07
     * @Param [name, password]
     * @Return com.nwq.entity.User
     */
    public User checkLogin(String name, String password) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(MdUtil.md5(password));
        return userDao.checkLogin(user);
    }

    /**
     * @Author nwq
     * @Version  1.0
     * @Description 根据账号修改密码
     * @Date 2020/6/29 18:06
     * @Param [username, newPs]
     * @Return void
     */
    public void updatePs(String username, String newPs) {
        userDao.updatePs(username, newPs);
    }
    public void updatePic(Integer id, String pic) {
        userDao.updatePic(id, pic);
    }

}
