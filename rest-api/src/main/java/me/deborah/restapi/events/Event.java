package me.deborah.restapi.events;

import lombok.*;

import java.time.LocalDateTime;

// lombok annotation은 meta annotation으로 사용할수 없음.
@Builder
@AllArgsConstructor // 기본 생성자
@NoArgsConstructor  // 모든 args 생성자
@Getter @Setter
@EqualsAndHashCode(of = "id")   // 상호 참조로 스텍오버플로우 발생하는 것 방지 (@Data 도 쓰면안됨)
public class Event {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus;
}
