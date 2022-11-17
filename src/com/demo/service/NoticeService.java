package com.demo.service;

import com.demo.vo.Notice;

import java.io.Serializable;
import java.util.Map;


public interface NoticeService {
    
    void add(Notice vo);

    
    void delete(long id);

   
    void update(Notice vo);

    
    Notice get(Serializable id);

   
    Map<String, Object> list(Map<String, Object> params);
}
