package me.deborah.corebasic.web;

import lombok.RequiredArgsConstructor;
import me.deborah.corebasic.common.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor

public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        // > 참고: requestURL을 MyLogger에 저장하는 부분은 컨트롤러 보다는
        // 공통 처리가 가능한 스프링 인터셉 터나 서블릿 필터 같은 곳을 활용하는 것이 좋다.
        // 여기서는 예제를 단순화하고, 아직 스프링 인터셉터를 학습 하지 않은 분들을 위해서 컨트롤러를 사용했다.
        // 스프링 웹에 익숙하다면 인터셉터를 사용해서 구현해보자.

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
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
}
