package com.demo.service.impl;

import com.demo.dao.CaigouDAO;
import com.demo.dao.impl.CaigouDAOImpl;
import com.demo.service.CaigouService;
import com.demo.vo.Caigou;

import java.io.Serializable;
import java.util.Map;


public class CaigouServiceImpl implements CaigouService {
    //@Override
    public void add(Caigou vo) {
        CaigouDAO caigouDAO = new CaigouDAOImpl();
        caigouDAO.add(vo);
    }

    //@Override
    public void delete(long id) {
        CaigouDAO caigouDAO = new CaigouDAOImpl();
        caigouDAO.delete(id);
    }

    //@Override
    public void update(Caigou vo) {
        CaigouDAO caigouDAO = new CaigouDAOImpl();
        caigouDAO.update(vo);
    }

    //@Override
    public Caigou get(Serializable id) {
        CaigouDAO caigouDAO = new CaigouDAOImpl();
        return caigouDAO.get(id);
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        CaigouDAO caigouDAO = new CaigouDAOImpl();
        return caigouDAO.list(params);
    }
}
