package com.demo.servlet;

import com.demo.util.Util;
import com.demo.service.CaigouService;
import com.demo.service.impl.CaigouServiceImpl;
import com.demo.vo.Caigou;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@WebServlet("/CaigouServlet")
public class CaigouServlet extends HttpServlet {

   
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //过滤编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = Util.decode(request, "action");
        if ("add".equals(action)) {//增加
            Caigou vo = new Caigou();
            //取出页面传进来的各个数据，并设置到Caigou对象的属性里
            vo.setCaigouName(Util.decode(request, "caigouName"));
            vo.setCaigouType(Util.decode(request, "caigouType"));
            vo.setCaigouCount(Util.decode(request, "caigouCount"));
            vo.setCaigouDate(Util.decode(request, "caigouDate"));
            vo.setCaigouText(Util.decode(request, "caigouText"));
            CaigouService caigouService = new CaigouServiceImpl();
            //调用Service层增加方法（add），增加记录
            caigouService.add(vo);
            this.redirectList(request, response);
        } else if ("delete".equals(action)) {//删除
            //取出表要删除的采购记录的主键
            long id = Long.parseLong(Util.decode(request, "id"));
            CaigouService caigouService = new CaigouServiceImpl();
            //调用Service层删除方法（delete），将对应的记录删除
            caigouService.delete(id);
            this.redirectList(request, response);
        } else if ("edit".equals(action)) {//修改
            //取出页面传进来的各个数据，并设置到Caigou对象的属性里
            Caigou vo = new Caigou();
            vo.setId(Long.valueOf(Util.decode(request, "id")));
            vo.setCaigouName(Util.decode(request, "caigouName"));
            vo.setCaigouType(Util.decode(request, "caigouType"));
            vo.setCaigouCount(Util.decode(request, "caigouCount"));
            vo.setCaigouDate(Util.decode(request, "caigouDate"));
            vo.setCaigouText(Util.decode(request, "caigouText"));
            CaigouService caigouService = new CaigouServiceImpl();
            //调用Service层更新方法（update），更新记录
            caigouService.update(vo);
            this.redirectList(request, response);
        } else if ("get".equalsIgnoreCase(action) || "editPre".equalsIgnoreCase(action)) {//根据主键ID，查询详情信息并跳转到详情页面或编辑页面
            Serializable id = Util.decode(request, "id");//取出页面传入的主键，用于查询详情
            CaigouService caigouService = new CaigouServiceImpl();
            Caigou vo = caigouService.get(id);
            request.getSession().setAttribute("vo", vo);
            String to = "get".equalsIgnoreCase(action) ? "info" : "edit";//判断是去详情显示页面还是编辑页面
            response.sendRedirect("caigou_" + to + ".jsp");
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
        CaigouService caigouService = new CaigouServiceImpl();
        Map<String, Object> map = caigouService.list(params);
        request.getSession().setAttribute("list", map.get("list"));

        Integer totalRecord = (Integer) map.get("totalCount");//根据查询条件取出对应的总记录数，用于分页
        String pageNum = Util.decode(request, "pageNum");//封装分页参数
        com.demo.util.PageBean<Object> pb = new com.demo.util.PageBean(Integer.valueOf(pageNum != null ? pageNum : "1"), totalRecord);
        params.put("startIndex", pb.getStartIndex());
        params.put("pageSize", pb.getPageSize());
        List list = (List) caigouService.list(params).get("list");//根据分页参数startIndex、pageSize查询出来的最终结果list
        pb.setServlet("CaigouServlet");
        pb.setSearchColumn(searchColumn);
        pb.setKeyword(keyword);
        pb.setList(list);
        request.getSession().setAttribute("pageBean", pb);
        request.getSession().setAttribute("list", pb.getList());

        response.sendRedirect("caigou_list.jsp");
    }
}
