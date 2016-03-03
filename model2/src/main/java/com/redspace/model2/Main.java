package com.redspace.model2;


import java.util.List;

public class Main {
    public static void main(String... args) {
        //new TheTask().execute();
        new UrlCall().loadMessages(new CallBack() {
        }, new Model() {
            @Override
            public void addMessage(String title, String message, CallBack call) {

            }

            @Override
            public void whenModelLoaded(CallBack call) {

            }

            @Override
            public Message getMessage(int i) {
                return null;
            }

            @Override
            public void editMessage(int i, String t, String m) {

            }

            @Override
            public List<Message> getMessages() {
                return null;
            }

            @Override
            public void deleteMessage(int i, CallBack call) {

            }

            @Override
            public void setData(List<Message> data) {

                System.out.print(data.size());
            }
        });
    }

    private static class TheTask extends AsyncTask<String>{


        @Override
        protected String doInBackground() {
            return "dsfsdf";
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.print(s);
        }
    }
}
