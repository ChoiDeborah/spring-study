package me.deborah.demowebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

//    @RequestMapping(
//            value = "/hello",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,       // 얘가 쓰임. 메소드가 오버라이딩 함. 둘다 쓰이는 거 아님.
//            produces = MediaType.TEXT_PLAIN_VALUE
//    )

    //-  특정한 타입의 데이터를 담고 있는 요청만 처리하는 핸들러
    // @RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    // Content-Type 헤더로 필터링
    // 매치 되지 않는 경우에 415 UnSupported Media Type 응답.

    // - 특정한 타입의 응답을 만드는 핸들러
    // @RequestMapping(produces="application/json")
    // Accept 헤더로 필터링
    // 매치 되지 않는 경우 406 Not Acceptable 응답

    // 클래스에 선언한 @RequestMapping에 사용한 것과 조합이 되지 않고 메소드에 사용한 @RequestMapping 의 설정으로 덮어쓴다.


    // - 헤더와 매개변수
    // 특정한 헤더가 있는 요청을 처리하고 싶은 경우
    // @RequestMapping(headers = “key”)
    // 특정한 헤더가 없는 요청을 처리하고 싶은 경우
    // @RequestMapping(headers = “!key”)
    // 특정한 헤더 키/값이 있는 요청을 처리하고 싶은 경우
    // @RequestMapping(headers = “key=value”)
    // 특정한 요청 매개변수 키를 가지고 있는 요청을 처리하고 싶은 경우
    // @RequestMapping(params = “a”)
    // 특정한 요청 매개변수가 없는 요청을 처리하고 싶은 경우
    // @RequestMapping(params = “!a”)
    // 특정한 요청 매개변수 키/값을 가지고 있는 요청을 처리하고 싶은 경우
    // @RequestMapping(params = “a=b”)

//    @RequestMapping(value = "/hello", headers = HttpHeaders.AUTHORIZATION + "=" + "111")

//    @GetMapping(value = "/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello";
//    }
//
//    @PostMapping("/hello")
//    @ResponseBody
//    public String helloPost() {
//        return "hello";
//    }

    // - 커스텀 애노테이션
    // @RequestMapping 애노테이션을 메타 애노테이션으로 사용하기
    //● @GetMapping 같은 커스텀한 애노테이션을 만들 수 있다.
    //
    // 메타(Meta) 애노테이션
    // ● 애노테이션에 사용할 수 있는 애노테이션
    // ● 스프링이 제공하는 대부분의 애노테이션은 메타 애노테이션으로 사용할 수 있다.
    // 조합(Composed) 애노테이션
    // ● 한개 혹은 여러 메타 애노테이션을 조합해서 만든 애노테이션
    // ● 코드를 간결하게 줄일 수 있다.
    // ● 보다 구체적인 의미를 부여할 수 있다.
    // @Retention
    // ● 해당 애노테이션 정보를 언제까지 유지할 것인가.
    // ● Source: 소스 코드까지만 유지. 즉, 컴파일 하면 해당 애노테이션 정보는 사라진다는 이야기.
    // ● Class: 컴파인 한 .class 파일에도 유지. 즉 런타임 시, 클래스를 메모리로 읽어오면 해당 정보는
    // 사라진다.
    // ● Runtime: 클래스를 메모리에 읽어왔을 때까지 유지! 코드에서 이 정보를 바탕으로 특정 로직을
    // 실행할 수 있다.
    //
    // @Target
    // ● 해당 애노테이션을 어디에 사용할 수 있는지 결정한다.
    //
    // @Documented
    // ● 해당 애노테이션을 사용한 코드의 문서에 그 애노테이션에 대한 정보를 표기할지 결정한다.
    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }

    // 맵핑 연습 문제
    // 1
    @GetMapping("/events")
    @ResponseBody
    public String events() {
        return "events";
    }

    // 2
    @GetMapping("/events/{id}")
    @ResponseBody
    public String getAnEvent(@PathVariable("id") int id) {
        return "event";
    }

    // 4
    @DeleteMapping(value = "/events/{id}")
    @ResponseBody
    public String removeAnEvent(@PathVariable("id") int id) {
        return "event";
    }
}
