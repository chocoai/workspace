//package com.whty.assis.api.utils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * \* User: zjd
// * \* Date: 2018/8/10
// * \* Time: 10:22
// * \* Description:
// * \
// */
//public class HttpServletRequestReplacedFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //Do nothing
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        ServletRequest requestWrapper = null;
//        if(request instanceof HttpServletRequest) {
//            requestWrapper = new MyRequestWrapper((HttpServletRequest) request);
//        }
//        if(null == requestWrapper) {
//            chain.doFilter(request, response);
//        } else {
//            chain.doFilter(requestWrapper, response);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        //Do nothing
//    }
//
//}