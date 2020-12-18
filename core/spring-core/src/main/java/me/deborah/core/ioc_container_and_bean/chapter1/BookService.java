package me.deborah.core.ioc_container_and_bean.chapter1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    // @Autowired 를 이용해 의존성 주입을 한다고 가정하자.
    //BookRepository bookRepository;

    // 1) 생성자
    //    @Autowired
    //    public BookService(BookRepository bookRepository) {
    //        this.bookRepository = bookRepository;
    //    }

    // 생성자 주입은 빈을 만들다가 빈에 필요한 다른 의존성이었던 북리파지스토리를 못찾아서 그에 해당하는 빈이 없어서
    //실패했구나


    // 2) Setter
//    @Autowired
//    public void setBookRepository(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    // 세터였는데 적어도 이 빈은 만들수 있는건 아닌가? 북서비스에 대한 인스턴스는 만들수 있지 않나?
    // Autowired라는 어노테이션이 있기 때문에 의존성을 주입을 하려고 시도 한다. 이 과정이 실패하는것
    // 인스턴스 자체는 만들수 있으나 Autowired 있기 때문에 실패하는 것
    // 이때 옵셔널 하게 변경하려면 @Autowired(required = false)를 하면됨
    // default값이 true 로 되어있다. 이렇게 변경 시 의존성 주입이 안된 상태로 빈이 등록됨

    // 3) 필드
    // 생성자를 이용한 의존성 주입보다는 약간 다르다.
    // 생성자는 빈을 만들 때에도 개입이 된다. 생성자에 전달받아야하는 타입에 해당하는 빈이 없으면 무조건 인스턴스를 만들지 못함
    // 세터와 필드 인젝션을 사용할 때는 북서비스가 사용하는 의존성 없이도 빈으로 등록되도록 할 수 있음.

    // BookRepository를 interface 로 만들고 ( MyBookRepository/ JiminBookRepository) 로 만든다.
    // 그러면 북 서비스에서는 어떤 북리파지토리를 주입을 해줄까? 스프링
    // 주입을 못해준다. 둘중에서 어떤걸 원하는지 스프링을 알 수 가없다.
    // 1) @Primary를 이용해 원하는 빈을 마킹을 해라
    // 2) 여러개에 해당하는 모든빈을 다 받거나
    //    @Autowired
    //    List<BookRepository> bookRepositories;

    // 3) Qualifier를 이용해 원하는 걸 마킹을 해라
    // 빈의 이름을 줘야함 (ex "jiminBookRepositoy")
    @Autowired
    BookRepository bookRepository;

    public void printBookRepository() {
        // 북 서비스에 어떤 리파지토리 빈이 주입되었는지 출력되는 매소드
        // @Primary / @Qualifier

        // 2) 여러개에 해당하는 모든 빈을 순회
        //this.bookRepositories.forEach(System.out::println);

        System.out.println(bookRepository.getClass());
    }

//    러너는 스프링 부트가 제공해주는 인터페이스이다.
//    애플리케이션이 다 등록 됐을 때 일을 하지만
//    아래의 경우 애플리케이션 구동 중에 등록된다.

//    @PostConstruct
//    public void setup(){
//        System.out.println(bookRepository.getClass());
//    }

}





