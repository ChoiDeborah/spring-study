package me.deborah.core.databinding.property_editor;

import java.beans.PropertyEditorSupport;

// PropertyEditor는 구현해야할 매소드가 많음.
public class EventEditor extends PropertyEditorSupport {
    // 공유하고 있는 Value가 PropertyEditor가 가지고 있는 값
    // 이 값이 서로다른 스레드에게 공유됨
    // State Pool임 (상태정보를 저장하고 있음) -> 스레드 세이프 하지 않음.
    // 따라서 Property Eidtor의 구현체들은 여러 스레드에 공유해서 쓰면 안됨.
    // 주의: 그냥 빈으로 등록해서 쓰면안됨!!!
    // Thread Scope Bean으로 만들어서 쓰면 되긴 함..
    // 쓰는 방법 @InitBinder 로 컨트롤러에 등록해서 쓰면 됨
    // 단점 또 한가지 오브젝트와 스트링 간의 변환만 할수 있다.
    @Override
    public String getAsText() {
        // PropertyEditor가 받은 객체를 getValue(Object 형태)로 가져올 수 있다.
        Event event = (Event)getValue();
        return event.getId().toString();
    }

    // TODO : 텍스트를 이벤트로 변한하는 걸 구현해야 함
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 값이 들어오는건 문자열(이벤트의 ID)이지만 우리는 숫자로 간주하여 변환.
        setValue(new Event(Integer.parseInt(text)));
    }
}
