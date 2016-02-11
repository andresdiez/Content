package com.example.adiez.content;


import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class DetailFragmentModel implements DetailFragmentPresenter.DetailModel {

    private String title;
    private String message;
    private int i;


    @Override
    public void setData(int i, DetailFragmentPresenter.DetailView view){
        this.i=i;
        new async(view).execute(i);
    }

    @Override
    public String getMessage() { return message; }

    @Override
    public String getTitle() { return title; }

    @Override
    public void setMessage(String message) { this.message=message; }

    @Override
    public void setTitle(String title) { this.title=title; }

    @Override
    public void saveAllData() { new async().execute(i); }




    private class async extends AsyncTask<Integer, String, Void> {


        DetailFragmentPresenter.DetailView view;

        public async(DetailFragmentPresenter.DetailView view) {
            this.view=view;
        }
        public async() {

        }

        @Override
        protected Void doInBackground(Integer... params) {

            String url = "http://172.16.11.20:8080/greeting?index="+params[0];

            if(view==null){url+="&title="+title+"&message="+message;}

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Message[] m = restTemplate.getForObject(url, Message[].class);

            title=m[0].getTitle();
            message=m[0].getMessage();
            return null;
        }

        @Override
        protected void onPostExecute(Void data) {
            super.onPostExecute(data);
            if(view!=null){view.displayMessage(title,message);}

        }
    }


}
