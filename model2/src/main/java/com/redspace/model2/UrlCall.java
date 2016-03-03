package com.redspace.model2;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UrlCall {
    private final String url = "http://172.16.9.254:8080/greeting";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Class<Message[]> object = Message[].class;
    private List<Message> messages;

    public UrlCall() {
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Loads data model
     */
    private abstract class LoadData extends AsyncTask<List<Message>> {
        private final CallBack callback;
        private Model model;

        protected LoadData(CallBack callback, Model model) {
            this.callback = callback;
            this.model = model;
        }

        @Override
        protected List<Message> doInBackground() {
            return doOperation();
        }

        @Override
        public void onPostExecute(List<Message> m) {

            model.setData(m);
            callback.updateView(m);
        }

        protected abstract List<Message> doOperation();
    }

    private class LoadTask extends LoadData {

        protected LoadTask(CallBack callback, Model model) {
            super(callback,model);
        }

        @Override
        protected List<Message> doOperation() {
            return new ArrayList(Arrays.asList(restTemplate.getForObject(url, object)));
        }
    }


    /**
     * Send data to server
     */
    private abstract static class SendData extends AsyncTask<List<Message>>{

        private CallBack callBack;
        private final String t;
        private final String m;

        public SendData(CallBack callBack,String t,String m) {

            this.callBack = callBack;
            this.t = t;
            this.m = m;
        }

        @Override
        protected List<Message> doInBackground() {

            return doOperation(t,m);
        }

        @Override
        public void onPostExecute(List<Message> l) {
            callBack.updateView(l);
        }

        protected abstract List<Message> doOperation(String t, String m);

    }

    private class SendMessage extends SendData{
        public SendMessage(CallBack callBack,String t,String m) {
            super(callBack,t,m);
        }

        @Override
        protected List<Message> doOperation(String t, String m) {
            restTemplate.put(url + "?title=" + t + "&message=" + m, null);
            messages.add(new Message(messages.size() + 1, t, m));
            return messages;
        }

    }


    /**
     * Delete data from server
     */

    private abstract class DeleteData extends AsyncTask<Void>{

        private final int i;
        private CallBack callBack;


        public DeleteData(int i, CallBack callBack) {
            this.i = i;
            this.callBack = callBack;
        }

        @Override
        protected Void doInBackground() {
            doOperation(i);
            return null;
        }
        @Override
        public void onPostExecute(Void v) {
            callBack.goBack();
        }

        protected abstract void doOperation(int i);

    }

    private class DeleteMessage extends DeleteData{

        public DeleteMessage(int i, CallBack callBack) {
            super(i,callBack);
        }

        @Override
        protected void doOperation(int i) {
            restTemplate.put(url + "?index=" + messages.get(i).getId()
                    + "&delete=true", null);
            messages.remove(i);
        }
    }


    /**
     * Update data from server
     */
    private abstract class UpdateData extends AsyncTask<Void>{
        private final int i;
        private final String t;
        private final String m;

        public UpdateData(int i, String t, String m) {

            this.i = i;
            this.t = t;
            this.m = m;
        }

        @Override
        protected Void doInBackground(){
            doOperation(i,t,m);
            return null;
        }
        protected abstract void doOperation(int i, String t, String m);

    }

    private class UpdateMessage extends UpdateData {

        public UpdateMessage(int i,String t,String m){
            super(i,t,m);
        }

        @Override
        protected void doOperation(int i, String t, String m) {
            restTemplate.put(url + "?index="+messages.get(i).getId()
                            +"&title=" + t
                            +"&message=" + m,
                    null);
            messages.set(i, new Message(i, t, m));
        }
    }


    public UrlCall(List<Message> messages) { this.messages = messages; }

    public void loadMessages(CallBack callback,Model model) { new LoadTask(callback,model).execute(); }

    public void addMessage(CallBack callBack,String t,String m){ new SendMessage(callBack,t,m).execute(); }

    public void deleteMessage(int i,CallBack callBack){
        new DeleteMessage(i,callBack).execute();
    }

    public void editMessage(int i,String t,String m) {
        new UpdateMessage(i,t,m).execute();
    }


}