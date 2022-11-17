package com.demo.dao;

import com.demo.vo.Notice;

import java.io.Serializable;
import java.util.Map;


public interface NoticeDAO {
   
    void add(Notice vo);

   
    boolean delete(long id);

  
    void update(Notice vo);

   
    Notice get(Serializable id);

   
    Map<String, Object> list(Map<String, Object> params);
}
