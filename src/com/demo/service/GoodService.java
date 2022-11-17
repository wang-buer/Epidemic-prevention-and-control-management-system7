package com.demo.service;

import com.demo.vo.Good;

import java.io.Serializable;
import java.util.Map;


public interface GoodService {
   
    void add(Good vo);

   
    void delete(long id);

  
    void update(Good vo);

   
    Good get(Serializable id);

    
    Map<String, Object> list(Map<String, Object> params);
}
