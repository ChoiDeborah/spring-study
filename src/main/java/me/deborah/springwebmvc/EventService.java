package me.deborah.springwebmvc;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class EventService {
    public List<Event> getEvents() {
        Event event1 = Event.builder()
                .name("스프링 웹 MVC 스터디 1차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2020, 11, 24, 16, 50))
                .endDataTime(LocalDateTime.of(2020, 11, 24, 18, 50))
                .build();
        Event event2 = Event.builder()
                .name("스프링 웹 MVC 스터디 2차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2020, 12, 1, 16, 50))
                .endDataTime(LocalDateTime.of(2020, 12, 1, 18, 50))
                .build();

        return  List.of(event1, event2);
    }
}
