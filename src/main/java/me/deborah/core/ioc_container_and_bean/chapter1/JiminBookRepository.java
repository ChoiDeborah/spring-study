package me.deborah.core.ioc_container_and_bean.chapter1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository @Primary
public class JiminBookRepository implements BookRepository {

}