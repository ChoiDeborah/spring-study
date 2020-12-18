package me.deborah.core.ioc_container_and_bean.chapter3_Autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service 라는 anotation이 붙으면 bin으로 등록된다.
@Service
public class BookService {

    // @Autowire 을 사용해 의존성 주입을 한다고 가정해보자
    BookRepository bookRepository;
    // 1) 생성자를 이용한 주입
    @Autowired
    // 해당 타입의 빈이 없는 경우
    // BookRepository가 bin으로 정의되지 않았을 경우 에러 발생
    // 무조건 전달 되는 인스턴스가 빈으로 등록 되어 있어야한다.
    // 북서비스도 등록 안됨

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // 생성자의 주입은 빈을 만들다가 빈에 필요한 다른 의존성이었던 BookRepository를 찾지못해 실패했다고 직관적으로 알수 있다.

    // 2) Setter를 이용한 등록
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // 해당 타입의 빈이 없는 경우
    // 세터인데 적어도 BookService 라는 인터페이스는 만들 수 있지 않나 하고 생각하지만
    // Autowired가 어노테이션을 실행하려고 하기 때문에 실패함
    // 이럴 경우 옵셔널 하게 (required = false) 인자를 주면 BookRepository는 주입하지 않은 채로 인터페이스 생성

    // 해당 타입의 빈이 여러개인 경우
    // ->BookRepository를 인터페이스로 만들고 여러 타입으로 확장한 경우
    // -@Primery
    // -해당 타입의 빈 모두 주입 받기
    //  리스트로 만든다
    // -@Qualifier

    // 동작 원리?
    // BeanPostProcessor 빈 라이프 사이클 인터페이스의 구현체에 의해 동작
    // BeanPostProcessor 란 무엇이냐?
    // Bean을 만든 후 (Bean을 Initialization 한 후 / 인스턴스화 한 후 )
    // 빈의 초기화 라이프 사이클 이전 또는 이후에 부가적인 작업을 할 수 있는 라이프 사이클 콜백
    // 동작 식
    // 빈팩토리가 빈포스트 프로세서를 찾음 그리고 다른 일반적인 빈들에 적용을 하는 것.

}
