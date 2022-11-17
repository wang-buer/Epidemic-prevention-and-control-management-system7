package com.demo.service;

import com.demo.vo.Caigou;

import java.io.Serializable;
import java.util.Map;


public interface CaigouService {
    
    void add(Caigou vo);

    
    void delete(long id);

   
    void update(Caigou vo);

    
    Caigou get(Serializable id);

   
    Map<String, Object> list(Map<String, Object> params);
}
