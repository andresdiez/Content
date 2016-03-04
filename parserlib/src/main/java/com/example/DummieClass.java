package com.example;

public class DummieClass {
    private final long id;
    private final String message;
    private final String title;


    public DummieClass(long id,
                   String message,
                   String title) {
        this.id = id;
        this.message = message;
        this.title = title;
    }

    public long getId() {return id;}


    public String getTitle() {return title;}


    public String getMessage(){return message;}
}
