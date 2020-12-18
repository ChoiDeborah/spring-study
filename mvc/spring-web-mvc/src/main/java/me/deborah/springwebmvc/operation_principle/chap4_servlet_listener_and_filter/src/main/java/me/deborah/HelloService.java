package me.deborah;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HelloService {
    public String getName() {
        return "Mozzi";
    }
}
