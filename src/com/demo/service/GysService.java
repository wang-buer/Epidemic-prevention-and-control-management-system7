package com.demo.service;

import com.demo.vo.Gys;

import java.io.Serializable;
import java.util.Map;


public interface GysService {
    
    void add(Gys vo);

    
    void delete(long id);

   
    void update(Gys vo);

    
    Gys get(Serializable id);

    
    Map<String, Object> list(Map<String, Object> params);
}
