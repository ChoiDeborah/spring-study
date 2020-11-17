package me.deborah.core.databinding.property_editor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getText() throws Exception {
        // mockMcv로 perform event 1 요청을 보낸다.
        // 숫자 이벤트 1을 이벤트 타입으로 변화할수 없기 때문에 실패.
        mockMvc.perform(get("/event/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}