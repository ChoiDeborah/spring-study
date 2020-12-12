package me.deborah.corebasic.web;

import lombok.RequiredArgsConstructor;
import me.deborah.corebasic.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor

public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId"); return "OK";
    }

    // 기대하는 출력
    //[ d06b992f...] request scope bean create
    // [d06b992f...][http://localhost:8080/log-demo] controller test
    // [d06b992f...][http://localhost:8080/log-demo] service id = testId
    // [d06b992f...] request scope bean close

    //실제는 기대와 다르게 애플리케이션 실행 시점에 오류 발생
    //  Error creating bean with name 'myLogger': Scope 'request' is not active for the
    //  current thread; consider defining a scoped proxy for this bean if you intend to
    //  refer to it from a singleton;

    // 스프링 애플리케이션을 실행 시키면 오류가 발생한다. 메시지 마지막에 싱글톤이라는 단어가 나오고...
    // 스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만,
    // request 스코프 빈은 아직 생성되지 않는다. 이 빈은 실제 고객의 요청이 와야 생성할 수 있다!

    // ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
    // ObjectProvider.getObject() 를 호출하시는 시점에는 HTTP 요청이 진행중이므로 request scope 빈의 생성이 정상 처리된다.
    // ObjectProvider.getObject() 를 LogDemoController , LogDemoService 에서 각각 한번씩 따로 호 출해도
    // 같은 HTTP 요청이면 같은 스프링 빈이 반환된다! 내가 직접 이걸 구분하려면 얼마나 힘들까 ᅲ ᅲ...
}
