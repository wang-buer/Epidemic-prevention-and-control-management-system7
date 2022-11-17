package com.demo.dao.impl;

import com.demo.util.Util;
import com.demo.dao.CaigouDAO;
import com.demo.vo.Caigou;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CaigouDAOImpl implements CaigouDAO {

    //@Override
    public void add(Caigou vo) {
        String sql = "insert into `t_caigou` (`caigou_name`,`caigou_type`,`caigou_count`,`caigou_date`,`caigou_text`) values(?,?,?,?,?)";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getCaigouName());
            ps.setString(2, vo.getCaigouType());
            ps.setString(3, vo.getCaigouCount());
            ps.setString(4, vo.getCaigouDate());
            ps.setString(5, vo.getCaigouText());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void update(Caigou vo) {
        String sql = "update `t_caigou` set `caigou_name` = ? ,`caigou_type` = ? ,`caigou_count` = ? ,`caigou_date` = ? ,`caigou_text` = ?  where `id` = ?";
        try {
            Connection c = Util.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, vo.getCaigouName());
            ps.setString(2, vo.getCaigouType());
            ps.setString(3, vo.getCaigouCount());
            ps.setString(4, vo.getCaigouDate());
            ps.setString(5, vo.getCaigouText());
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
            String sql = "delete from `t_caigou` where id = " + id;
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
    public Caigou get(Serializable id) {
        Caigou vo = null;
        try {
            Connection c = Util.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from `t_caigou` where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                vo = new Caigou();
                vo.setId(rs.getLong("id"));
                vo.setCaigouName(rs.getString("caigou_name"));
                vo.setCaigouType(rs.getString("caigou_type"));
                vo.setCaigouCount(rs.getString("caigou_count"));
                vo.setCaigouDate(rs.getString("caigou_date"));
                vo.setCaigouText(rs.getString("caigou_text"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    //@Override
    public Map<String, Object> list(Map<String, Object> params) {
        List<Caigou> list = new ArrayList();
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
                sqlList = "select * from `t_caigou` where 1=1 " + condition + " order by id asc " + limit + ";";
                ps = c.prepareStatement(sqlList);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Caigou vo = new Caigou();
                    vo.setId(rs.getLong("id"));
                    vo.setCaigouName(rs.getString("caigou_name"));
                    vo.setCaigouType(rs.getString("caigou_type"));
                    vo.setCaigouCount(rs.getString("caigou_count"));
                    vo.setCaigouDate(rs.getString("caigou_date"));
                    vo.setCaigouText(rs.getString("caigou_text"));
                    list.add(vo);
                }
            String sqlCount = "select count(*) from `t_caigou` where 1=1 " + condition;
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
