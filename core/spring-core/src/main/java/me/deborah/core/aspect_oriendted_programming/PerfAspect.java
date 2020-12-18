package me.deborah.core.aspect_oriendted_programming;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 어노테이션 기반의 스프링 AOP를 쓰고 있기 때문에 컴포넌트 스캔을 할것이라 빈으로 등록했음.
@Component
@Aspect
// 해야할 일(Advice)과, 해야할일을 어디에 등록(PointCut)할 것인가.
// 를 정의해야
public class PerfAspect {

    // ProceedingJoinPoint 어드바이스가 적용되는 대상
    // createEvent, publishEvent 메서드를 감싸고 있다고 생각하면 됨.

    // 메서드를 실행하는 것 자체가 proceed이고 proceed시 에러가 발생할수 있기 때문에 Throwable 던져줌
    // 타겟에 해당하는 메서드를 호출하고 결과값을 리턴해줌

    // Advice를 어떻게 적용할 것인가?
    // PointCut 을 excution으로 정의했음
    //@Around("execution(* me.deborah.core..*.EventService.*(..))") // 메서드를 감싸는 형태로 적용이 됨 메서드 호출 전후로 뭔가를 할수 있음. 에러 발생시에도 뭔가 할수 있음.
    // 단 이경우에는 원하지 않는 곳에도 해당 테스크를 실행할 수 있음

    // 이경우엔 에노테이션을 이용한 방법이 있다.
    // 에노테이션을 붙인 곳에만 적용이 됨 해당 방법을 추천.
    @Around("@annotation(PerfLogging)")

    // 빈을 이용하는 방법도 있음
    // 빈이 가지고 있는 모든 퍼블릭 메소드에 적용이 됨
    //@Around("bean(simpeEventService)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }

    // 어떤 메소드가 실행되기 이전에 뭔갈 하고싶다.
    @Before("bean(simpeEventService)")
    public void hello() {
        System.out.println("Hello");
    }
}
