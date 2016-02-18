package com.example.adiez.content.model;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class DataLoader extends AsyncTask<Void,Integer,List<Message>> {


    private final CallBackInterface call;
    private String url = "http://172.16.11.20:8080/greeting";
    private final RestTemplate restTemplate = new RestTemplate();
    Class<Message[]> object = Message[].class;

    public DataLoader(CallBackInterface call){
        this.call=call;
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    protected List<Message> doInBackground(Void... params) {
        return Arrays.asList(restTemplate.getForObject(url, object));
    }

    @Override
    protected void onPostExecute(List<Message> messages) {
        super.onPostExecute(messages);
        call.didLoad(messages);
    }
}
