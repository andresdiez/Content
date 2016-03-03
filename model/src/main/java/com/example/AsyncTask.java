package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public abstract class AsyncTask <Result>{

    public static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    public void onPreExecute(){}

    abstract protected Result doInBackground();

    public void onPostExecute(Result result){}

    private final List<Result> results= Collections.synchronizedList(new ArrayList<Result>());
    public void execute() {


        onPreExecute();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    results.add(doInBackground());


                }
            });

            try {
                t.start();
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        onPostExecute(results.get(0));


//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                final Result result=doInBackground();
//
//
//                //Platform.runLater is a javafx code that executes onPost in Main Thread.
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        onPostExecute(result);
//                    }
//                });
//
//            }
//        });
//        t.start();



    }








}
