package com.multithreading.chapter2.servlet;

import com.multithreading.annotation.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * count++包含‘读，改，写’三个复合操作
 * 用线程安全类保证原子操作
 */
@ThreadSafe
public class CountingFactorizer implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Integer i = Integer.valueOf(request.getParameter("i"));
        i = factors(i);
        count.incrementAndGet();
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
