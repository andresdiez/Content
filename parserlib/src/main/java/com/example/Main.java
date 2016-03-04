package com.example;



import java.util.List;

public class Main {



    public static void main(String args[]) {


        String url="http://172.16.9.254:8080/greeting";
        UrlParser con=new UrlParser(url,DummieClass.class);
        List<DummieClass> messages= (List<DummieClass>) con.getValues();
        System.out.println("size: " + messages.size());


    }





}
