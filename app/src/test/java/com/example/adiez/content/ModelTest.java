package com.example.adiez.content;



import org.junit.Test;



public class ModelTest {
    @Test
    public void addMessageValidator(){

        Model m= new Model();

        m.getMessage();
        System.out.print(m.addMessage("","").get(0).message);

    }
}