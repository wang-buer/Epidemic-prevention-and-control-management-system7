package com.demo.dao;

import com.demo.vo.Gys;

import java.io.Serializable;
import java.util.Map;


public interface GysDAO {
    
    void add(Gys vo);

   
    boolean delete(long id);

    
    void update(Gys vo);

    
    Gys get(Serializable id);

   
    Map<String, Object> list(Map<String, Object> params);
}
