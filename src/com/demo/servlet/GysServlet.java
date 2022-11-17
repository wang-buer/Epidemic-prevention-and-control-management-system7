package com.demo.servlet;

import com.demo.util.Util;
import com.demo.service.GysService;
import com.demo.service.impl.GysServiceImpl;
import com.demo.vo.Gys;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@WebServlet("/GysServlet")
public class GysServlet extends HttpServlet {

    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //过滤编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = Util.decode(request, "action");
        if ("add".equals(action)) {//增加
            Gys vo = new Gys();
            //取出页面传进来的各个数据，并设置到Gys对象的属性里
            vo.setGysNo(Util.decode(request, "gysNo"));
            vo.setGysName(Util.decode(request, "gysName"));
            vo.setGysContact(Util.decode(request, "gysContact"));
            vo.setGysPhone(Util.decode(request, "gysPhone"));
            vo.setGysText(Util.decode(request, "gysText"));
            GysService gysService = new GysServiceImpl();
            //调用Service层增加方法（add），增加记录
            gysService.add(vo);
            this.redirectList(request, response);
        } else if ("delete".equals(action)) {//删除
            //取出表要删除的供应商记录的主键
            long id = Long.parseLong(Util.decode(request, "id"));
            GysService gysService = new GysServiceImpl();
            //调用Service层删除方法（delete），将对应的记录删除
            gysService.delete(id);
            this.redirectList(request, response);
        } else if ("edit".equals(action)) {//修改
            //取出页面传进来的各个数据，并设置到Gys对象的属性里
            Gys vo = new Gys();
            vo.setId(Long.valueOf(Util.decode(request, "id")));
            vo.setGysNo(Util.decode(request, "gysNo"));
            vo.setGysName(Util.decode(request, "gysName"));
            vo.setGysContact(Util.decode(request, "gysContact"));
            vo.setGysPhone(Util.decode(request, "gysPhone"));
            vo.setGysText(Util.decode(request, "gysText"));
            GysService gysService = new GysServiceImpl();
            //调用Service层更新方法（update），更新记录
            gysService.update(vo);
            this.redirectList(request, response);
        } else if ("get".equalsIgnoreCase(action) || "editPre".equalsIgnoreCase(action)) {//根据主键ID，查询详情信息并跳转到详情页面或编辑页面
            Serializable id = Util.decode(request, "id");//取出页面传入的主键，用于查询详情
            GysService gysService = new GysServiceImpl();
            Gys vo = gysService.get(id);
            request.getSession().setAttribute("vo", vo);
            String to = "get".equalsIgnoreCase(action) ? "info" : "edit";//判断是去详情显示页面还是编辑页面
            response.sendRedirect("gys_" + to + ".jsp");
        } else {//默认去列表页面
            this.redirectList(request, response);
        }
    }

    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);//Get请求和Post请求的处理是一样的，所以把request、response转交给Post方法就好
    }

   
    private void redirectList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查询列和关键字
        String searchColumn = Util.decode(request, "searchColumn");
        String keyword = Util.decode(request, "keyword");
        Map<String, Object> params = new HashMap();//用来保存控制层传进来的参数(查询条件)
        params.put("searchColumn", searchColumn);//要查询的列
        params.put("keyword", keyword);//查询的关键字
        GysService gysService = new GysServiceImpl();
        Map<String, Object> map = gysService.list(params);
        request.getSession().setAttribute("list", map.get("list"));

        Integer totalRecord = (Integer) map.get("totalCount");//根据查询条件取出对应的总记录数，用于分页
        String pageNum = Util.decode(request, "pageNum");//封装分页参数
        com.demo.util.PageBean<Object> pb = new com.demo.util.PageBean(Integer.valueOf(pageNum != null ? pageNum : "1"), totalRecord);
        params.put("startIndex", pb.getStartIndex());
        params.put("pageSize", pb.getPageSize());
        List list = (List) gysService.list(params).get("list");//根据分页参数startIndex、pageSize查询出来的最终结果list
        pb.setServlet("GysServlet");
        pb.setSearchColumn(searchColumn);
        pb.setKeyword(keyword);
        pb.setList(list);
        request.getSession().setAttribute("pageBean", pb);
        request.getSession().setAttribute("list", pb.getList());

        response.sendRedirect("gys_list.jsp");
    }
}
