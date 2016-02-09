package com.example.adiez.content;



import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


public class Model {




    public List<Message> getMessage()  {


        final String url = "http://172.16.11.20:8080/greeting";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Message[] m = restTemplate.getForObject(url, Message[].class);



        return Arrays.asList(m);
    }


    public static List<Message> addMessage(String title, String message){

        final String url = String.format("http://172.16.11.20:8080/greeting?title=%s&message=%s",title,message);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Message[] m = restTemplate.getForObject(url, Message[].class);


        return Arrays.asList(m);
    }






}
