package com.example;




public abstract class AsyncTask <Result>{



    public void onPreExecute(){}

    abstract protected Result doInBackground();

    public void onPostExecute(Result result){}

    public void execute() {


        onPreExecute();




        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {


                final Result result=doInBackground();
                onPostExecute(result);


            }
        });
        t.start();



    }








}
