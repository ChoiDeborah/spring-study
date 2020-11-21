package me.deborah.core.aspect_oriendted_programming;

import java.lang.annotation.*;

// 에노테이션 만들 때 주의점 RetentionPolicy를 CLASS(기본값) 이상으로 줘야 함.
// RetentionPolicy란 어노테이션 정보를 얼마나 유지할 것인가?
// .CLASS로 하면 바이트 코드까지 남아있음.
// .SOURCE로 하면 컴파일 하고 사라짐.
// 굳이 런타임 까지 유지할 필요없음.

/*
 * 이 어노테이션을 사용하면 성능을 로깅해 줍니다.
 */
@Documented // 자바 독 만들 때 다큐먼트 되도록
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)

public @interface PerfLogging {
}
