package me.deborah.core.ioc_container_and_bean.chapter3_Autowire;

import org.springframework.stereotype.Repository;

//@Component
// Repository는 Repository로 하는 것이 좋다.
// Repository로 등록한 모든 빈의 특정한 기능을 사용할수도 있고 구분하는 게 좋음
@Repository
public class BookRepository {
}
