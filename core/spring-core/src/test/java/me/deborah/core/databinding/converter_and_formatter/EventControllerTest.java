package me.deborah.core.databinding.converter_and_formatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)

// 계층형 태스트 웹과 관련된 빈만 등록을 해주는 테스트를 만든것
// 웹과 관련된 것이니 컨트롤러들만 주로 등록이됨
// 따라서 포매터, 컨버터가 제대로 등록이 안되면 깨질 위험이 있다.
// WebMvcTest안에 이 테스트에 필요한 컨버터나 포매터를 빈으로 등록을 하면 된다.

//@WebMvcTest({EventFormatter.class, EventController.class})
// 그냥 클래스만 준다고 빈으로 등록해주진 않음 컴포넌트 스캔이 가능한 빈이어야한다.
// 컴포넌트 또는 컨트롤러.. 빈으로 등록된 다음부터는 컨버터가 동작을 하니 테스트가 잘 동작한다.

// 명시적으로 아래처럼 해놓는게 좋아보임.
@WebMvcTest({
        EventConverter.StringToEventConverter.class,
        EventController.class})
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