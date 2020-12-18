package me.deborah.core.databinding.converter_and_formatter;

// 웹쪽은 거의 사용자 입력값이 문자열이다.
// 따라서 객체들을 문자로 내보내는경우가 많다.
// 그런 문자들을 메세지 소스를 사용해서 메세지 다국화(i18n)
// 어떤 메세지 키로 실제 location을 여러나라 언어에 해당하는 메세지로 읽어오는 방법
// 그런 식으로 지역화 해야할 경우도 있기 때문에....
// Fommater를 제공함.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

// Formmater의 경우에는 포메터로 처리할 타입 하나를 준다.

// Formmater또한 Thread Safe하므로 Bean으로 등록하여 사용이 가능(= 다른 빈을 주입받을 수 도 있다는 말)
@Component
public class EventFormatter implements Formatter<Event> {

    @Autowired
    MessageSource messageSource;

    // 프로퍼티 에디터랑 비슷함
    // 하나는 문자를 받아서 객체로
    // 하나는 객체를 받아서 문자로
    // 다른 점은 로케일 정보를 받아서 처리할 수 있다는 것
    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        return new Event(Integer.parseInt(text));
    }

    @Override
    public String print(Event event, Locale locale) {
        // 로케일 정보를 써서 메세지를 만들고 싶다면 요런 식으로도 쓸수 있다.
        //messageSource.getMessage("title", locale);
        return event.getId().toString();
    }
}
