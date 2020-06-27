package com.nwq.service;

import com.nwq.dao.MenuDao;
import com.nwq.entity.Menu;

import java.util.List;

/**
 * @auth nwq
 * @date 2020/6/22
 * @Description
 */
public class MenuService {

    private MenuDao menuDao = new MenuDao();

    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
