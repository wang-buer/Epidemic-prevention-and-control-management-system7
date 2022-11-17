package com.demo.service.impl;

import com.demo.dao.GoodDAO;
import com.demo.dao.impl.GoodDAOImpl;
import com.demo.service.GoodService;
import com.demo.vo.Good;

import java.io.Serializable;
import java.util.Map;


public class GoodServiceImpl implements GoodService {
    //@Override
    public void add(Good vo) {
        GoodDAO goodDAO = new GoodDAOImpl();
        goodDAO.add(vo);
    }

    //@Override
    public void delete(long id) {
        GoodDAO goodDAO = new GoodDAOImpl();
        goodDAO.delete(id);
    }

    //@Override
    public void update(Good vo) {
        GoodDAO goodDAO = new GoodDAOImpl();
        goodDAO.update(vo);
    }

    //@Override
    public Good get(Serializable id) {
        GoodDAO goodDAO = new GoodDAOImpl();
        return goodDAO.get(id);
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        GoodDAO goodDAO = new GoodDAOImpl();
        return goodDAO.list(params);
    }
}
