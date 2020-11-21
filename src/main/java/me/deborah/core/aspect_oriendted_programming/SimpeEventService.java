package me.deborah.core.aspect_oriendted_programming;

import org.springframework.stereotype.Service;

// RealService
@Service
public class SimpeEventService implements EventService {

    @Override
    public void createEvent() {
        //long begin = System.currentTimeMillis();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Create an event");
        //System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void publishEvent() {
        //long begin = System.currentTimeMillis();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Published an envet");
        //System.out.println(System.currentTimeMillis() - begin);
    }

    public void deleteEvent() {
        System.out.println("Deleted an event");
    }

}
