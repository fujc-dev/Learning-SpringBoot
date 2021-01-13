package com.zc58s.springbootbase.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 自定义过滤器
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/13 9:32
 */
@WebFilter(filterName = "userFilter", urlPatterns = "/home/*")  //过滤URL，拦截以/home开头的所有地址，拦截之后，将
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" ------------------> init ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(" ------------------> doFilter ");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println(" ------------------> destroy ");
    }
}
