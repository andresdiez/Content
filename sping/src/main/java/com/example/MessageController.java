package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private List<Message> list=new ArrayList<>();
    private int id=0;

    @RequestMapping("/greeting")
    public List<Message> greeting(@RequestParam(value = "title", defaultValue = "") String title,
                                  @RequestParam(value = "message", defaultValue = "") String message) {


        if (title.equals("")){
            return list;
        }
        else {
            addValue(title,message);
            return list;
        }

    }


    public void addValue(String title, String message){
        list.add(0,new Message(id,title,message) );
        id++;
    }

}