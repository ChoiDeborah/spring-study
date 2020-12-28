package me.deborah.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class SampleController {

    //@RequestMapping(value = "/hello", method = RequestMethod.GET)     // 핸들러에 특정 문자열을 매핑하고싶다
                                                                        // 허용하고 싶은 HTTP method (GET만 허용)
                                                                        //@GetMapping("/hello) 와 동일

    //@RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.PUT}) // GET, PUT 둘 다 허용하고 싶다. 배열 사용.

    // 요청 식별자로 맵핑하기
    //@RequestMapping은 다음의 패턴을 지원한다.
    //
    // - ?  : 한 글자      (“/author/???” => “/author/123”)
    // - *  : 여러 글자     (“/author/*” => “/author/mozzi”)
    // - ** : 여러 패스     (“/author/** => “/author/mozzi/book”)
    //
    //(“/author/???” => “/author/123”) (“/author/*” => “/author/keesun”)
    //(“/author/** => “/author/keesun/book”)

    //@RequestMapping({"/hello", "/hi"})      // 여러개의 문자열로 맵핑 가능

    // -클래스에 선언한 RequestMapping과 조합
    //   - 클래스에 선언한 URI 패턴 뒤에 붙여서 팹핑 가능.
    // - 정규 표현식으로 매핑 가능

    @RequestMapping("/mozzi")       // - URI 확장자 매핑 지원
                                    // spring에서는 "/mozzi.*" 에 대한 맵핑 도 암묵적으로 해준다.
                                    // spring boot 에서는 지원 안됨 보안이슈(RFD Attack) 때문에

    @ResponseBody
    public String helloMozzi() {
        return "hello mozzi";
    }

    @RequestMapping("{name:[a-z]+}")
    @ResponseBody                           // 문자열을 응답으로 보내고 싶을 경우
    public String hello(@PathVariable String name) {

        return "hello " + name;                     // 해당 이름의 View로 찾아 감.
    }

    // 둘다 맵핑 될 경우 좀 더 자세한 것에 우선 순위가 간다.
}
