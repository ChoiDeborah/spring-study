package me.deborah.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    //@RequestMapping(value = "/hello", method = RequestMethod.GET)     // 핸들러에 특정 문자열을 매핑하고싶다
                                                                        // 허용하고 싶은 HTTP method (GET만 허용)
                                                                        //@GetMapping("/hello) 와 동일

    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.PUT}) // GET, PUT 둘 다 허용하고 싶다. 배열 사용.
    @ResponseBody // 문자열을 응답으로 보내고 싶을 경
    public String hello() {
        return "hello"; // 해당 이름의 View로 찾아 감.
    }
}
