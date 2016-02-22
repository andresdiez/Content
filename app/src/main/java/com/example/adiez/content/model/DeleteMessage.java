package com.example.adiez.content.model;

import android.app.Activity;
import android.os.AsyncTask;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DeleteMessage extends AsyncTask<Integer,Integer,Void> {


    private final RestTemplate restTemplate = new RestTemplate();
    private List<Message> messages;
    private Activity activity;

    public DeleteMessage(List<Message> messages, Activity activity) {

        this.messages = messages;

        this.activity = activity;
    }



    @Override
    protected Void doInBackground(Integer... params) {

        int i=params[0];
        String url = "http://172.16.11.20:8080/greeting";
        restTemplate.put(url + "?index=" + messages.get(params[0]).getId()
                + "&delete=true", null);
        messages.remove(i);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activity.onBackPressed();
    }
}