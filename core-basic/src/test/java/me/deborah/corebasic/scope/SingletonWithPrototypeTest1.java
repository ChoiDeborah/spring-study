package me.deborah.corebasic.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        //@Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic() {
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    // 실행해보면 ac.getBean() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
    // 의존관계를 외부에서 주입(DI) 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것을 Dependency Lookup (DL) 의존관계 조회(탐색) 이라한다.
    // 그런데 이렇게 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워진다.
    // 지금 필요한 기능은 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 딱! DL 정도의 기능만 제공하는 무언가가 있으면 된다.
    // 스프링에는 이미 모든게 준비되어 있다.
    // ObjectFactory, ObjectProvider
    // 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공하는 것이 바로 ObjectProvider 이다. 참고로 과거에는 ObjectFactory 가 있었는데, 여기에 편의 기능을 추가해서 ObjectProvider 가 만들어졌다.

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    // 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점
    //스프링 컨테이너에 프토토타입 스코프의 빈을 요청하면 항상 새로운 객체 인스턴스를 생성해서 반환한다.
    // 하지만 싱글톤 빈과 함께 사용할 때는 의도한 대로 잘 동작하지 않으므로 주의해야 한다

    // clientBean 은 싱글톤이므로, 보통 스프링 컨테이너 생성 시점에 함께 생성되고, 의존관계 주입도 발생한다.
    // 1. clientBean 은 의존관계 자동 주입을 사용한다. 주입 시점에 스프링 컨테이너에 프로토타입 빈을 요청 한다.
    // 2. 스프링 컨테이너는 프로토타입 빈을 생성해서 clientBean 에 반환한다. 프로토타입 빈의 count 필드 값은 0이다.
    // 이제 clientBean 은 프로토타입 빈을 내부 필드에 보관한다. (정확히는 참조값을 보관한다.)

    // 클라이언트 A는 clientBean 을 스프링 컨테이너에 요청해서 받는다.싱글톤이므로 항상 같은 clientBean 이 반환된다.
    // 3. 클라이언트 A는 clientBean.logic() 을 호출한다.
    // 4. clientBean 은 prototypeBean의 addCount() 를 호출해서 프로토타입 빈의 count를 증가한다. count값이 1이 된다.

    // 클라이언트 B는 clientBean 을 스프링 컨테이너에 요청해서 받는다.싱글톤이므로 항상 같은 clientBean 이 반환된다.
    // 여기서 중요한 점이 있는데, clientBean이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다. 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성이 된 것이지, 사용 할 때마다 새로 생성되는 것이 아니다!
    // 5. 클라이언트 B는 clientBean.logic() 을 호출한다.
    // 6. clientBean 은 prototypeBean의 addCount() 를 호출해서 프로토타입 빈의 count를 증가한다. 원래 count 값이 1이었으므로 2가 된다.

    // 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 그런데 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에,
    // 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함 께 계속 유지되는 것이 문제다.
    // 아마 원하는 것이 이런 것은 아닐 것이다. 프로토타입 빈을 주입 시점에만 새로 생성하는게 아니라, 사용할 때 마다 새로 생성해서 사용하는 것을 원할 것이다.


    //> 참고: 여러 빈에서 같은 프로토타입 빈을 주입 받으면, 주입 받는 시점에 각각 새로운 프로토타입 빈이 생성 된다. 예를 들어서 clientA, clientB가 각각 의존관계 주입을 받으면 각각 다른 인스턴스의 프로토타입 빈을 주입 받는다.
    //> clientA prototypeBean@x01
    //> clientB prototypeBean@x02
    //> 물론 사용할 때 마다 새로 생성되는 것은 아니다.

    // 실행해보면 prototypeBeanProvider.getObject() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
    // ObjectProvider 의 getObject() 를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반 환한다. (DL)
    // 스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워진다.
    // ObjectProvider 는 지금 딱 필요한 DL 정도의 기능만 제공한다.
    // 특징
    // ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존
    // ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 없음, 스프링에 의존
    // JSR-330 Provider
    // 마지막 방법은 javax.inject.Provider 라는 JSR-330 자바 표준을 사용하는 방법이다.
    // 이 방법을 사용하려면 javax.inject:javax.inject:1 라이브러리를 gradle에 추가해야 한다.

    // 실행해보면 provider.get() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
    // provider 의 get() 을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다. (DL)
    // 자바 표준이고, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워진다.
    // Provider 는 지금 딱 필요한 DL 정도의 기능만 제공한다.
    // 특징
    // get() 메서드 하나로 기능이 매우 단순하다.
    // 별도의 라이브러리가 필요하다.
    // 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.
    // 정리
    // 그러면 프로토타입 빈을 언제 사용할까? 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요하면 사용하면 된다.
    // 그런데 실무에서 웹 애플리케이션을 개발해보면, 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에
    // 프로토타입 빈을 직접적으로 사용하는 일은 매우 드물다.
    // ObjectProvider , JSR330 Provider 등은 프로토타입 뿐만 아니라 DL이 필요한 경우는 언제든지 사용 할 수 있다.

    // > 참고: 스프링이 제공하는 메서드에 @Lookup 애노테이션을 사용하는 방법도 있지만, 이전 방법들로 충분하 고, 고려해야할 내용도 많아서 생략하겠다.
    // > 참고: 실무에서 자바 표준인 JSR-330 Provider를 사용할 것인지, 아니면 스프링이 제공하는 ObjectProvider를 사용할 것인지 고민이 될 것이다.
    // ObjectProvider는 DL을 위한 편의 기능을 많이 제 공해주고 스프링 외에 별도의 의존관계 추가가 필요 없기 때문에 편리하다.
    // 만약(정말 그럴일은 거의 없겠지 만) 코드를 스프링이 아닌 다른 컨테이너에서도 사용할 수 있어야 한다면 JSR-330 Provider를 사용해야한다.
    // > 스프링을 사용하다 보면 이 기능 뿐만 아니라 다른 기능들도 자바 표준과 스프링이 제공하는 기능이 겹칠때 가 많이 있다.
    // 대부분 스프링이 더 다양하고 편리한 기능을 제공해주기 때문에, 특별히 다른 컨테이너를 사용 할 일이 없다면, 스프링이 제공하는 기능을 사용하면 된다.
}
