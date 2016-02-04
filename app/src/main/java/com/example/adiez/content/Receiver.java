package com.example.adiez.content;



import android.os.AsyncTask;

import java.util.List;


public class Receiver implements Presenter.Receiver {

    private final Model model;

    public Receiver(Model model) {
        this.model=model;
    }

    @Override
    public List<Message> getMessages() {
        return model.getMessage();
    }

    @Override
    public void addMessage(String title, String message, Presenter.Handler handler
    ) {

        new Async(handler).execute(title, message);

    }



    private class Async extends AsyncTask<String,String,List<Message>>{


        private final Presenter.Handler handler;

        public Async(Presenter.Handler handler) {
            this.handler=handler;
        }

        @Override
        protected List<Message> doInBackground(String... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return model.addMessage(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            super.onPostExecute(messages);
            handler.onDataChange();
        }
    }


}