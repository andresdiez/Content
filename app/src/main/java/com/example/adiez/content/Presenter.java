package com.example.adiez.content;





import java.util.List;


public class Presenter {

    private final Receiver receiver;
    private final Handler handler;

    public Presenter(Receiver receiver, Handler handler){

        this.receiver=receiver;
        this.handler=handler;
    }




    interface Receiver{
        List<Message> getMessages();
        void addMessage(String title, String message, Handler handler);
        void loadMessages(Handler handler);
    }

    interface Handler{
        void onDataChange(List<Message> messages);
        void dataDidLoad();
    }



    public void loadMessages() {
        receiver.loadMessages(handler);
    }

    public void getMessages(){handler.onDataChange(receiver.getMessages());}

    public void addMessage(String title, String message){ receiver.addMessage(title, message, handler); }











}
