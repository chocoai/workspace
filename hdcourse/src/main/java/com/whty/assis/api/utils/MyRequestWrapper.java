//package com.whty.assis.api.utils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * \* User: zjd
// * \* Date: 2018/8/10
// * \* Time: 10:06
// * \* Description:
// * \
// */
//public class MyRequestWrapper extends HttpServletRequestWrapper {
//
//    private final String body;
//
//    public MyRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
//
//        StringBuilder sb = new StringBuilder();
//        String line;
//        BufferedReader reader = request.getReader();
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//
//        body = sb.toString();
//    }
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
//        return new ServletInputStream() {
//            @Override
//            public int read() throws IOException {
//                return bais.read();
//            }
//        };
//    }
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(this.getInputStream()));
//    }
//
//}