package com.example.adiez.content.model;

import android.os.AsyncTask;

import com.example.adiez.content.CallBack;

import org.springframework.web.client.RestTemplate;

import java.util.List;


public class AddMessage extends AsyncTask<String,Integer,Void> {


    private final RestTemplate restTemplate = new RestTemplate();
    private List<Message> messages;
    private CallBack callBack;

    public AddMessage(List<Message> messages, CallBack callBack) {

        this.messages = messages;
        this.callBack = callBack;
    }


    @Override
    protected Void doInBackground(String... params) {
        String url = "http://172.16.11.20:8080/greeting";
        restTemplate.put(url + "?title=" + params[0] + "&message=" + params[1], null);
        messages.add(new Message(messages.size()+1,params[0],params[1]));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callBack.updateView();
    }
}