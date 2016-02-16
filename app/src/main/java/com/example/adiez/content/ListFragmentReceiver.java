package com.example.adiez.content;



import android.os.AsyncTask;

import java.util.List;


public class ListFragmentReceiver implements ListFragmentPresenter.Receiver {

    private final ListFragmentModel model;

    public ListFragmentReceiver(ListFragmentModel model) {
        this.model=model;
    }

    @Override
    public List<Message> getMessages() {
        return model.getMessages();
    }

    @Override
    public void addMessage(String title, String message, ListFragmentPresenter.Handler handler){
        new Async(handler).execute(title, message);
    }

    @Override
    public void loadMessages(final ListFragmentPresenter.Handler handler) {
        new Async2(handler).execute();



    }


    /**
     * Async calls
     */

    private class Async extends AsyncTask<String,String,List<Message>>{


        private final ListFragmentPresenter.Handler handler;

        public Async(ListFragmentPresenter.Handler handler) {
            this.handler=handler;
        }


        @Override
        protected List<Message> doInBackground(String... params) {

            model.addMessage(params[0], params[1]);
            return model.getMessages();
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            super.onPostExecute(messages);
            handler.onDataChange(messages);
        }
    }




    private class Async2 extends AsyncTask<String,String,Void>{

        ListFragmentPresenter.Handler handler;
        public Async2(ListFragmentPresenter.Handler handler){
            this.handler=handler;
        }
        @Override
        protected Void doInBackground(String... params) {
            model.loadMessages();
            return null;
        }


        @Override
        protected void onPostExecute(Void messages) {
            super.onPostExecute(messages);
            handler.dataDidLoad();

        }
    }





}
