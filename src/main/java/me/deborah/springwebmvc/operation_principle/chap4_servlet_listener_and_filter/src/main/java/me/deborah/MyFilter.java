package me.deborah;

import javax.servlet.*;
import java.io.IOException;

// 서블릿 컨테이너의 요청을 받았을 떄, 필터를 거쳐서 간다.
// 필터 등록 web.xml <filter>
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter");
        // 요청응답이 전달이 되지 않기 때문에, Filter Chain을 doFilter(다음 필터)로 연결
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroy");
    }
}
