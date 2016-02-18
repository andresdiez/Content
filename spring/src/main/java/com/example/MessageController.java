package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
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
                                  @RequestParam(value = "message", defaultValue = "") String message,
                                  @RequestParam(value = "index", defaultValue = "") String index,
                                  @RequestParam(value = "getcount", defaultValue = "false") String getcount,
                                  @RequestParam(value = "delete", defaultValue = "") String delete) {

        if (!index.equals("")&&!title.equals("")){
            editValue(Integer.parseInt(index),title,message);
            return list;
        }
        else if(!index.equals("")&&!delete.equals("")){
            list.remove(Integer.parseInt(index));
            return list;
        }
        else if(!index.equals("")){
            return Arrays.asList(list.get(Integer.parseInt(index)));
        }
        else if(getcount.equals("true")){

            return list; //Arrays.asList(list.get(list.size()-1));
        }
        else if (title.equals("")){
            return list;
        }
        else {
            addValue(title,message);
            return list;
        }

    }


    @RequestMapping("/count")
    public Count count(){
        return new Count(id);

    }


    public void addValue(String title, String message){
        list.add(new Message(id,title,message) );
        id++;
    }
    public void editValue(int i,String title, String message){
        list.set(i,new Message(id,title,message));
    }

}
