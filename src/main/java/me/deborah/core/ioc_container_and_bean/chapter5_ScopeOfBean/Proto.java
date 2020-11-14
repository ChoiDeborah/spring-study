package me.deborah.core.ioc_container_and_bean.chapter5_ScopeOfBean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component @Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class Proto {
}
