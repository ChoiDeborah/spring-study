package me.deborah.core.ioc_container_and_bean.chapter8_ApplicationContext_ApplicationEventPublisher;

// 이 이벤트는 빈으로 등록되는 게 아
// 원하는 데이터를 담아서 전송할 수 있는 이벤트가 될 수도 있고
// 이벤트를 발생시킬 소스만 전달하고 있다.
// 원하는 데이터가 있다면 실어서 전송할 수 있음.

// 데이터를 담아서 전송할 수 있는 이벤트가 있고
// 이 이벤트를 발생시킬 수 있는 기능을 ApplicationContext가 가지고 있는것임.
// 4.2 이전 까지는 아래 코드로
//public class MyEvent extends ApplicationEvent {
//    private int data;
//    public MyEvent(Object source) {
//        super(source);
//    }
//
//    public MyEvent(Object source, int data) {
//        super(source);
//        this.data = data;
//    }
//
//    public int getData() {
//        return data;
//    }
//}

// 4.2 이후 부터는 Event가 ApplicationEvent 상속 받을 필요가 없게 되었다.
public class MyEvent {
    private int     data;
    private Object  source;

    public MyEvent(Object source, int data) {
        this.source = source;
        this.data = data;
    }

    // 비 침투성
    // 스프링 기반의 코드가 내 코드에 들어가지 않는것
    public Object getSource() {
        return source;
    }

    public int getData() {
        return data;
    }
}