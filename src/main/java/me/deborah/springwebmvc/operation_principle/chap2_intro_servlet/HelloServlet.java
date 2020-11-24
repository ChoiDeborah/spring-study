package me.deborah.springwebmvc.operation_principle.chap2_intro_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servelt
// - 자바 엔터프라이즈 에디션은 웹 어플리케이션 개발용 스팩과 API를 제공한다.
// - 요청 당 쓰레드 (만들거나, 풀에서 가져다가) 사용
// - 그중에 가장 중요한 클래스 중 하나가 HttpServlet

// 서블릿 어플리케이션은 직접 실행할 수 없음 서블릿 컨테이너가 실행함.

public class HelloServlet extends HttpServlet {
    // 실행하는 방법?
    // 생명 주기

    // 최초 요청을 받았을 때 한번 초기화
    // 인스턴스가 생성 된 후는 이 과정이 생략
    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    // 서블릿이 초기화 된 다음 부터 클라이언트의 요청을 처리할 수 있다.
    // 각 요청은 별도의 쓰레드로 처리하고 이때 서블릿 인스턴스의 service 메소드를 호출한다
    // - 이 안에서 HTTP 요청을 받고 클라로 보낼 HTTP응답을 만든다.
    // - service는 보통 HTTP Method에 따라 doGet, doPost() 등으로 처리를 위임한다.
    // 따라서 보통 doGet()또는 doPost()를 구현한다.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.getWriter().write("Hello Servlet");
    }

    // 서블릿을 더이상 메모리에 올려놓을 필요가 없다고 판단되면
    // 서블릿 메모리에서 내려야 할 시점에 destory를 호출
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
