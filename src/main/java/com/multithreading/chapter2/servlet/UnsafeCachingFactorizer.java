package com.multithreading.chapter2.servlet;

import com.multithreading.annotation.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 尽管AtomicReference是线程安全的，但是service()存在竞争条件
 * 无法保证会同时更新lastNumber和lastFactors，也不能保证每个线程都能同时获取两个值
 */
@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {

    private final AtomicReference<Integer> lastNumber = new AtomicReference<>();
    private final AtomicReference<Integer> lastFactors = new AtomicReference<>();

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Integer i = Integer.valueOf(request.getParameter("i"));
        if (i.equals(lastNumber.get())) {
            //to do sth...
        }else {
            Integer factors = factors(i);
            lastNumber.set(i);
            lastFactors.set(factors);
        }

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
