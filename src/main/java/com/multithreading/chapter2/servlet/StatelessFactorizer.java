package com.multithreading.chapter2.servlet;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;

/**
 * 一个无状态的servlet（不包含域也没有引用其他类的域）：无状态的对象永远是安全的
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {


    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Integer i = Integer.valueOf(request.getParameter("i"));
        i = factors(i);
        System.out.println(i);

    }

    private Integer factors(Integer i) {
        if (i == 1) {
            return 1;
        } else {
            return i * factors(i - 1);
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
