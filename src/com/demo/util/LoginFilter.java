package com.demo.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {

    //@Override
    public void destroy() {
    }

    //@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        //过滤编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //移除错误提示
        session.removeAttribute("alert_msg");
        //登录拦截
        String uri = request.getRequestURI();
        String action = request.getParameter("action");
        if (uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || "register".equalsIgnoreCase(action) || "validationCode".equalsIgnoreCase(action) || "login".equalsIgnoreCase(action) || uri.contains("/include/") || uri.contains("/img/") || uri.contains("/js/")) {
            chain.doFilter(request, response);
            return;
        } else if (session.getAttribute("loginUser") == null) {
            session.setAttribute("alert_msg", "错误：请先登录！");
            response.sendRedirect("login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    //@Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}