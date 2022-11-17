package com.demo.service.impl;

import com.demo.dao.GysDAO;
import com.demo.dao.impl.GysDAOImpl;
import com.demo.service.GysService;
import com.demo.vo.Gys;

import java.io.Serializable;
import java.util.Map;


public class GysServiceImpl implements GysService {
    //@Override
    public void add(Gys vo) {
        GysDAO gysDAO = new GysDAOImpl();
        gysDAO.add(vo);
    }

    //@Override
    public void delete(long id) {
        GysDAO gysDAO = new GysDAOImpl();
        gysDAO.delete(id);
    }

    //@Override
    public void update(Gys vo) {
        GysDAO gysDAO = new GysDAOImpl();
        gysDAO.update(vo);
    }

    //@Override
    public Gys get(Serializable id) {
        GysDAO gysDAO = new GysDAOImpl();
        return gysDAO.get(id);
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        GysDAO gysDAO = new GysDAOImpl();
        return gysDAO.list(params);
    }
}
