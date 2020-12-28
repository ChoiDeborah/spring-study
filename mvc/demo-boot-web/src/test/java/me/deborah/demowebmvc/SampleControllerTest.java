package me.deborah.demowebmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.rmi.server.ExportException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
       mockMvc.perform(get("/hello"))
               .andDo(print())
               .andExpect(status().isOk())                              // 어떤 값이 나오기를 기대한다.
               .andExpect(content().string("hello"))
       ;


        mockMvc.perform(put("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
        ;


        mockMvc.perform(post("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed())               // GET, PUT 만 허용 했으니 405 Error
        ;
    }





}