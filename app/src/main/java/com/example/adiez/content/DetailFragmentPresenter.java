package com.example.adiez.content;


public class DetailFragmentPresenter {

    private final DetailModel model;
    private final DetailView view;



    public DetailFragmentPresenter(DetailFragmentModel model,DetailFragment view) {

        this.model=model;
        this.view=view;


    }

    public void setMessage(String message) { model.setMessage(message); }

    public void setTitle(String title) { model.setTitle(title); }

    public void onDataChange() { view.displayMessage(model.getTitle(), model.getMessage()); }

    public void saveData() { model.saveAllData(); }

    public void displayMessage(int i){ model.setData(i, view);}

    public String getTitle(){ return model.getTitle(); }

    public String getMessage(){ return model.getMessage(); }



    interface DetailView{

        void displayMessage(String t,String m);

    }


    interface DetailModel {

        void setData(int i, DetailView view);

        String getMessage();

        String getTitle();

        void setMessage(String message);

        void setTitle(String title);

        void saveAllData();
    }







}
