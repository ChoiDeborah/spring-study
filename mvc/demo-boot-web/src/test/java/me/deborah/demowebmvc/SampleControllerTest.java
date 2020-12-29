package me.deborah.demowebmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.rmi.server.ExportException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
//       mockMvc.perform(
//               get("/hello")
//               .contentType(MediaType.APPLICATION_JSON_UTF8)
//               .accept(MediaType.APPLICATION_JSON)
//       )
//               .andDo(print())
//               //.andExpect(status().isUnsupportedMediaType()) // 지원하지 않는 Media Type 415 Code
//               .andExpect(status().isNotAcceptable())
//       ;

       // 헤더와 매개변수

//        mockMvc.perform(get("/hello")
//                    //.header(HttpHeaders.AUTHORIZATION, "111")
//                    .param("name", "mozzi")
//        )
//                .andDo(print())
//                .andExpect(status().isOk())
//        ;

        // HEAD와 OPTIONS 요청 처리

        // 우리가 구현하지 않아도 스프링 웹 MVC에서 자동으로 처리하는 Method
        // - HEAD
        // : GET 요청과 동일하지만 응답 본문을 받아오지 않고 응답 헤더만 받아온다.
        // - OPTIONS
        //  : 사용할 수 있는 HTTP Method 제공
        //    서버 또는 특정 리소스가 제공하는 기능을 확인할 수 있다.
        //    서버는 Allow 응답 헤더에 사용할 수 있는 HTTP Method 목록을 제공해야 한다.

        mockMvc.perform(options("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().stringValues(HttpHeaders.ALLOW,
                        // 문자열 순서 상관 없도록
                        hasItems(containsString("GET"),
                                containsString("POST"),
                                containsString("OPTIONS"),
                                containsString("HEAD"))))
        ;
    }





}