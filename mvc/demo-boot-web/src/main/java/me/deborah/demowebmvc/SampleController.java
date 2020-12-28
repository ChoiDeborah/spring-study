package me.deborah.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {


    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,       // 얘가 쓰임. 메소드가 오버라이딩 함. 둘다 쓰이는 거 아님.
            produces = MediaType.TEXT_PLAIN_VALUE
    )

    //-  특정한 타입의 데이터를 담고 있는 요청만 처리하는 핸들러
    // @RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    // Content-Type 헤더로 필터링
    // 매치 되지 않는 경우에 415 UnSupported Media Type 응답.

    // - 특정한 타입의 응답을 만드는 핸들러
    // @RequestMapping(produces="application/json")
    // Accept 헤더로 필터링
    // 매치 되지 않는 경우 406 Not Acceptable 응답

    // 클래스에 선언한 @RequestMapping에 사용한 것과 조합이 되지 않고 메소드에 사용한 @RequestMapping 의 설정으로 덮어쓴다.
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
