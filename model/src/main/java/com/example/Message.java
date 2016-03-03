package com.example;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Message {
    private final long id;
    private final String message;
    private final String title;

    @JsonCreator
    public Message(@JsonProperty("id") long id,
                   @JsonProperty("message") String message,
                   @JsonProperty("title") String title) {
        this.id = id;
        this.message = message;
        this.title = title;
    }

    public long getId() {return id;}


    public String getTitle() {return title;}


    public String getMessage(){return message;}

}
