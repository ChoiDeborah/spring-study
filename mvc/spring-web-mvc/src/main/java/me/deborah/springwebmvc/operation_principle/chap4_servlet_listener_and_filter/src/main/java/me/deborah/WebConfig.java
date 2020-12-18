package me.deborah;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
// 컴포넌트 스캔을 하는데, 디폴트 필터는 사용하지 않고, 오직 컨트롤러만 Bean으로 등록하겠다.
@ComponentScan
public class WebConfig {
}
