package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private List<Greeting> list=new ArrayList<>();
    private int id=0;

    @RequestMapping("/greeting2")
    public List<Greeting> greeting(@RequestParam(value="title", defaultValue="") String title,
                                   @RequestParam(value="message", defaultValue="") String message) {


        if (title.equals("")){
            return list;
        }
        else {
            addValue(title,message);
            return list;
        }

    }


    public void addValue(String title, String message){
        list.add(new Greeting(id,title,message) );
        id++;
    }

}
