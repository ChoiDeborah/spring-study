package me.deborah.demowebmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
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

        mockMvc.perform(get("/hello")
                    //.header(HttpHeaders.AUTHORIZATION, "111")
                    .param("name", "mozzi")
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }





}