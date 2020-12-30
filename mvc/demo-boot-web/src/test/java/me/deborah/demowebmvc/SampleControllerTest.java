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
    public void getEvents() throws Exception {
        mockMvc.perform(post("/events?name=mozzi")
                    .param("name, ","mozzi")
                    .param("limit", "20"))  // 파라미터로 문자열만 줄 수 있음
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("mozzi"));
    }
}