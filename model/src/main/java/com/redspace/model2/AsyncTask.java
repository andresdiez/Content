package com.redspace.model2;

import android.os.Handler;


public abstract class AsyncTask <Result>{

    private Handler handler;

    public void onPreExecute(){}

    abstract protected Result doInBackground();

    public void onPostExecute(Result result){}

    public void execute() {


        onPreExecute();


        handler = new Handler();

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {


                final Result result=doInBackground();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
					}
				});

            }
        });
        t.start();



    }








}
