package com.example;

/**
 * Created by adiez on 2016-02-09.
 */
public class Message {

    private final long id;
    private String title;
    private String message;

    public Message(long id, String title, String message) {
        this.id = id;
        this.title=title;
        this.message=message;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage(){
        return message;
    }


}
