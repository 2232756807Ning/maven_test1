package com.nwq.service;

import com.nwq.dao.DeptDao;
import com.nwq.entity.Dept;

import java.util.List;

/**
 * @auth nwq
 * @data 2020/6/28
 * @Description
 */


public class DeptService  {
    private DeptDao deptDao = new DeptDao();

    public List<Dept> listAll() {
        return deptDao.listAll();
    }

}
