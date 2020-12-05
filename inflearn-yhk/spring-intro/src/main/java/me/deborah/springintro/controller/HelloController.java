package me.deborah.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // MVC 템플릿 엔진 방식
    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody   // Response body부에 직접 데이터를 넣겠다.
                    // What's the different?
                    // viewResolver 대신 HttpMessageConverter가 동작
                    // 문자의 경우 StringHttpMessageConverter에 의해 template의 view를 거치지 않고 그대로 데이터를 내려줌.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody   // 문자가 아니라 hello 객체가 왔네?
                    // MappingJackson2HttpMessageConverter 의해 json 형식으로 웹브라우져로 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
