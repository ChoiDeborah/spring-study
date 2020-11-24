package me.deborah.springwebmvc.operation_principle.chap1_intro_spring_mvc;

import lombok.*;

import java.time.LocalDateTime;

// lombok annotation 사용 시 컴파일 시점에 자동으로 스테틱 클래스들이 제
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Event {

    private  String name;

    private int limitOfEnrollment;

    private LocalDateTime startDateTime;

    private LocalDateTime endDataTime;

}
