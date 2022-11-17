package com.demo.dao.impl;

import com.demo.util.Util;
import com.demo.dao.GysDAO;
import com.demo.vo.Gys;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GysDAOImpl implements GysDAO {

    //@Override
    public void add(Gys vo) {
        String sql = "insert into `t_gys` (`gys_no`,`gys_name`,`gys_contact`,`gys_phone`,`gys_text`) values(?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getGysNo());
            ps.setString(2, vo.getGysName());
            ps.setString(3, vo.getGysContact());
            ps.setString(4, vo.getGysPhone());
            ps.setString(5, vo.getGysText());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void update(Gys vo) {
        String sql = "update `t_gys` set `gys_no` = ? ,`gys_name` = ? ,`gys_contact` = ? ,`gys_phone` = ? ,`gys_text` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getGysNo());
            ps.setString(2, vo.getGysName());
            ps.setString(3, vo.getGysContact());
            ps.setString(4, vo.getGysPhone());
            ps.setString(5, vo.getGysText());
            ps.setLong(6, vo.getId());
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
            String sql = "delete from `t_gys` where id = " + id;
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
    public Gys get(Serializable id) {
        Gys vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `t_gys` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Gys();
                vo.setId(rs.getLong("id"));
                vo.setGysNo(rs.getString("gys_no"));
                vo.setGysName(rs.getString("gys_name"));
                vo.setGysContact(rs.getString("gys_contact"));
                vo.setGysPhone(rs.getString("gys_phone"));
                vo.setGysText(rs.getString("gys_text"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        List<Gys> list = new ArrayList();
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
                sqlList = "select * from `t_gys` where 1=1 " + condition + " order by id asc " + limit + ";";
                ps = c.prepareStatement(sqlList);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Gys vo = new Gys();
                    vo.setId(rs.getLong("id"));
                    vo.setGysNo(rs.getString("gys_no"));
                    vo.setGysName(rs.getString("gys_name"));
                    vo.setGysContact(rs.getString("gys_contact"));
                    vo.setGysPhone(rs.getString("gys_phone"));
                    vo.setGysText(rs.getString("gys_text"));
                    list.add(vo);
                }
            String sqlCount = "select count(*) from `t_gys` where 1=1 " + condition;
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
