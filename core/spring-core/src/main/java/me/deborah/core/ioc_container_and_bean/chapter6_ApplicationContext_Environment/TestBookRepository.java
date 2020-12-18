package me.deborah.core.ioc_container_and_bean.chapter6_ApplicationContext_Environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
//@Component @Profile("test")
public class TestBookRepository implements  BookRepository{
}
