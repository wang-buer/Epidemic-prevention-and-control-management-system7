package com.demo.dao;

import com.demo.vo.Caigou;

import java.io.Serializable;
import java.util.Map;


public interface CaigouDAO {
    
    void add(Caigou vo);

  
    boolean delete(long id);

  
    void update(Caigou vo);

   
    Caigou get(Serializable id);

   
    Map<String, Object> list(Map<String, Object> params);
}
