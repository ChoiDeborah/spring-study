package me.deborah.core.ioc_container_and_bean.chapter5_ScopeOfBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
//    @Autowired
//    Single single;
//
//    @Autowired
//    Proto proto;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // 아래의 두 인스턴스가 같다.
//        // 싱글 톤이기 때문에.
//        System.out.println(proto);
//        System.out.println(single.getProto());
//    }

    // 경우에 따라서는 여러가지 다른 스코프를 쓸 수 있음
    // 해당 인스턴스를 어떤 특정한 스코프에 따라 새로 만들어야하는 경우가 있으면
    // 프로토 타입 스코프는 매번 새로운 인스턴스를 만들어서 사용해야하는 스코프다.
    //

    @Autowired
    ApplicationContext ctx;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("proto");

        System.out.println(ctx.getBean(Proto.class));
        System.out.println(ctx.getBean(Proto.class));
        System.out.println(ctx.getBean(Proto.class));

        System.out.println("single");
        System.out.println(ctx.getBean(Single.class));
        System.out.println(ctx.getBean(Single.class));
        System.out.println(ctx.getBean(Single.class));

        System.out.println("proto by single");

        // 프로토 타입의 빈인데 계속 같은 레퍼런스임.

        System.out.println(ctx.getBean(Single.class).getProto());
        System.out.println(ctx.getBean(Single.class).getProto());
        System.out.println(ctx.getBean(Single.class).getProto());

        // 해결법?
        // 1) 프록시 모드를 설정해 주는거
        // proxyMode = ScopedProxyMode.TARGET_CLASS
        // 클래스 기반의 프록시 로 감싸서 쓰라고 알려주는 것
        // 싱글이 프록시를 거쳐서 사용해
        // 매번 새로운 타입의 프록시로 쓰도록 CG라이브러리...
        // 인터페이스 프록시 밖에 못만듦.
        // 프록시 기반의 클래스를 만들어서 CG라이브러리 기반을 상속받아서 쓰는거
        // 프록시 기반의 인스턴스가 생성되는것

        // 2) ObjectProvider
        // 인스턴스가 매번 달라짐...
    }

    // 프로토 타입 빈이 싱글톤 타입을 쓴다? 문제없음 매번 같은 싱글톤 인스턴스이기 때문
    // 반대의 경우
    // 프로토 타입의 프로퍼티도 세팅이 됨.
    // 프로토 타입의 프로퍼티가 변경되지 않아서 문제임.

}
