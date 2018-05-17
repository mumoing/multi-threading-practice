package com.multithreading.chapter2.servlet;

import com.multithreading.annotation.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;

/**
 * 添加了命中数count来计算处理请求的数量，在多线程的情况下会出现‘遗失更新’
 * 产生‘读-写-改’操作
 */
@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {

    //命中数
    private long count = 0;

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Integer i = Integer.valueOf(request.getParameter("i"));
        i = factors(i);
        count++;
        System.out.println(i);
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }

    private Integer factors(Integer i) {
        if (i == 1) {
            return 1;
        } else {
            return i * factors(i - 1);
        }
    }
}

