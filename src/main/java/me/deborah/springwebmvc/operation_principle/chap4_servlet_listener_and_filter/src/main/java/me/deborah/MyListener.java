package me.deborah;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// 서블릿 컨텍스트 라이프 사이클 이벤트
// 리스너를 등록하려면 web.xml <Listener> 태그로 등록

// 리스너는 서블릿 컨테이너 위에 있는 개념.
// 리스너들이 서블릿 컨테이너 변경이 감지될수 있도록 서블릿 컨테이너에 등록되고
// 서블릿 컨테이너의 요청을 받았을 떄, 필터를 거쳐서 간다.
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialized");
        // 서블릿 컨텍스트에 에트리 뷰트를 추가 할 수 있다, 이름 키값 (암의의 객체 오브젝트로 넣을 수 있다)
        sce.getServletContext().setAttribute("name", "deborah");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ContextDestroyed");
    }
}
