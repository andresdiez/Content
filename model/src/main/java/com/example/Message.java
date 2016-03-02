package com.example;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private final long id;
    @NonNull private final String message;
    @NonNull private final String title;

    @JsonCreator
    public Message(@JsonProperty("id") long id,
                   @JsonProperty("message") @NonNull String message,
                   @JsonProperty("title") @NonNull String title) {
        this.id = id;
        this.message = message;
        this.title = title;
    }

    public long getId() {return id;}

    @NonNull
    public String getTitle() {return title;}

    @NonNull
    public String getMessage(){return message;}
}
