package com.demo.dao;

import com.demo.vo.Good;

import java.io.Serializable;
import java.util.Map;

/**
 * 物资模块的DAO层（数据层）接口，提供增删改查等数据库操作的方法抽象
 */
public interface GoodDAO {
    
    void add(Good vo);

   
    boolean delete(long id);

 
    void update(Good vo);

    
    Good get(Serializable id);

   
    Map<String, Object> list(Map<String, Object> params);
}
