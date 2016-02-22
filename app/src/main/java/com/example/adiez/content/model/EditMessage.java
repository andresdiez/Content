package com.example.adiez.content.model;

import android.os.AsyncTask;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class EditMessage extends AsyncTask<String,Integer,Void> {


    private final RestTemplate restTemplate = new RestTemplate();
    private List<Message> messages;
    private int i;

    public EditMessage(List<Message> messages,int i) {

        this.messages = messages;
        this.i = i;
    }


    @Override
    protected Void doInBackground(String... params) {
        String url = "http://172.16.11.20:8080/greeting";
        restTemplate.put(url + "?index="+messages.get(i).getId()
                +"&title=" + params[0]
                +"&message=" + params[1],
                null);
        messages.set(i, new Message(i, params[1], params[0]));
        return null;
    }


}