package com.example.adiez.content;



import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


public class ListFragmentModel {


    private String url = "http://172.16.11.20:8080/greeting";
    private RestTemplate restTemplate = new RestTemplate();
    private List<Message> messages;
    Class<Message[]> object = Message[].class;


    public ListFragmentModel(){
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

    }


    public List<Message> getMessages()  { return messages; }


    public void addMessage(String title, String message){

        String urlCalls="?title="+title+"&message="+message;
        this.messages=Arrays.asList(restTemplate.getForObject(url+urlCalls, object));

    }


    public void loadMessages() {
        this.messages=Arrays.asList(restTemplate.getForObject(url, object));
    }


}
