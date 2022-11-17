package com.demo.dao.impl;

import com.demo.util.Util;
import com.demo.dao.GoodDAO;
import com.demo.vo.Good;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GoodDAOImpl implements GoodDAO {

    //@Override
    public void add(Good vo) {
        String sql = "insert into `t_good` (`wupin_name`,`wupin_no`,`wupin_count`,`wupin_type`,`wupin_gys`,`wupin_guige`,`wupin_price`,`wupin_text`) values(?,?,?,?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getWupinName());
            ps.setString(2, vo.getWupinNo());
            ps.setString(3, vo.getWupinCount());
            ps.setString(4, vo.getWupinType());
            ps.setString(5, vo.getWupinGys());
            ps.setString(6, vo.getWupinGuige());
            ps.setString(7, vo.getWupinPrice());
            ps.setString(8, vo.getWupinText());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void update(Good vo) {
        String sql = "update `t_good` set `wupin_name` = ? ,`wupin_no` = ? ,`wupin_count` = ? ,`wupin_type` = ? ,`wupin_gys` = ? ,`wupin_guige` = ? ,`wupin_price` = ? ,`wupin_text` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getWupinName());
            ps.setString(2, vo.getWupinNo());
            ps.setString(3, vo.getWupinCount());
            ps.setString(4, vo.getWupinType());
            ps.setString(5, vo.getWupinGys());
            ps.setString(6, vo.getWupinGuige());
            ps.setString(7, vo.getWupinPrice());
            ps.setString(8, vo.getWupinText());
            ps.setLong(9, vo.getId());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public boolean delete(long id) {
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "delete from `t_good` where id = " + id;
            s.execute(sql);
            s.close();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //@Override
    public Good get(Serializable id) {
        Good vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `t_good` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Good();
                vo.setId(rs.getLong("id"));
                vo.setWupinName(rs.getString("wupin_name"));
                vo.setWupinNo(rs.getString("wupin_no"));
                vo.setWupinCount(rs.getString("wupin_count"));
                vo.setWupinType(rs.getString("wupin_type"));
                vo.setWupinGys(rs.getString("wupin_gys"));
                vo.setWupinGuige(rs.getString("wupin_guige"));
                vo.setWupinPrice(rs.getString("wupin_price"));
                vo.setWupinText(rs.getString("wupin_text"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        List<Good> list = new ArrayList();
        int totalCount = 0;
        String condition = "";
        String sqlList;
        if (params.get("searchColumn") != null && !"".equals(params.get("searchColumn"))) {
            condition += " and `" + params.get("searchColumn") + "` like '%" + params.get("keyword") + "%'";
        }
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            String limit = (params.get("startIndex") != null && params.get("pageSize") != null) ? " limit " + params.get("startIndex") + "," + params.get("pageSize") : "";
                sqlList = "select * from `t_good` where 1=1 " + condition + " order by id asc " + limit + ";";
                ps = c.prepareStatement(sqlList);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Good vo = new Good();
                    vo.setId(rs.getLong("id"));
                    vo.setWupinName(rs.getString("wupin_name"));
                    vo.setWupinNo(rs.getString("wupin_no"));
                    vo.setWupinCount(rs.getString("wupin_count"));
                    vo.setWupinType(rs.getString("wupin_type"));
                    vo.setWupinGys(rs.getString("wupin_gys"));
                    vo.setWupinGuige(rs.getString("wupin_guige"));
                    vo.setWupinPrice(rs.getString("wupin_price"));
                    vo.setWupinText(rs.getString("wupin_text"));
                    list.add(vo);
                }
            String sqlCount = "select count(*) from `t_good` where 1=1 " + condition;
            ps = c.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap();
        result.put("list", list);
        result.put("totalCount", totalCount);
        return result;
    }
}
