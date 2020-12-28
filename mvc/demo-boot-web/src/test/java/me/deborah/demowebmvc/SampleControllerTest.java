package me.deborah.demowebmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.rmi.server.ExportException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
       mockMvc.perform(get("/hello/mozzi"))
               .andDo(print())
               .andExpect(status().isOk())                              // 어떤 값이 나오기를 기대한다.
               .andExpect(content().string("hello mozzi"))
       ;

       // 핸들러 자체에 대한 테스트
        mockMvc.perform(get("/hello/mozzi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello mozzi"))
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("helloMozzi"))
        ;

//        mockMvc.perform(get("/hi"))
//                .andDo(print())
//                .andExpect(status().isOk())
//        ;
    }





}