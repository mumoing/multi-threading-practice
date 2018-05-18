package com.multithreading.chapter2.servlet;


import net.jcip.annotations.ThreadSafe;
import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 用synchronized保证线程安全
 */
@ThreadSafe
public class SynchronizedFactorizer implements Servlet {

    private final AtomicReference<Integer> lastNumber = new AtomicReference<>();
    private final AtomicReference<Integer> lastFactors = new AtomicReference<>();

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public synchronized void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
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
