package me.deborah.core.ioc_container_and_bean.chapter6_ApplicationContext_Environment;

import me.deborah.core.ioc_container_and_bean.chapter5_ScopeOfBean.Proto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Single {

    @Autowired
    private Proto proto;

    @Autowired
    private ApplicationContext applicationContext;

    public Proto getProto() {
        return proto;
    }
}
