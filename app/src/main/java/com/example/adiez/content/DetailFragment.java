package com.example.adiez.content;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements DetailFragmentPresenter.DetailView{


    DetailFragmentPresenter presenter;
    private int i;
    public boolean dataHasChange =false;
    private boolean status=false;

    @Bind(R.id.button2) Button saveButton;
    @Bind(R.id.textView3) TextView title;
    @Bind(R.id.textView4) TextView message;
    @Bind(R.id.editText3) EditText eTitle;
    @Bind(R.id.editText4) EditText eMessage;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter=new DetailFragmentPresenter(new DetailFragmentModel(),this);
        presenter.displayMessage(i);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_filter){
            editMessage();
            return true;
        }
        else if (id == R.id.action_delete){
            deleteMessage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void deleteMessage() {
        new AlertDialog.Builder(this.getActivity())
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.deleteMessage();
                        status=true;
                        getActivity().onBackPressed();



                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.detail_fragment, container, Boolean.parseBoolean(null));
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public void setPosition(int i){
        this.i=i;
    }

    @Override
    public void displayMessage(String t, String m) {
        title.setText(t);
        message.setText(m);

    }


    public void editMessage(){

        eTitle.setText(presenter.getTitle());
        eMessage.setText(presenter.getMessage());

        getActivity().findViewById(R.id.relative1).setVisibility(getView().VISIBLE);
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.setMessage(eMessage.getText().toString());
                        presenter.setTitle(eTitle.getText().toString());
                        presenter.onDataChange();
                        getActivity().findViewById(R.id.relative1).setVisibility(getView().GONE);
                        dataHasChange = true;

                    }
                }
        );


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Communicator com=(Communicator)getActivity();
        if (dataHasChange){presenter.saveData(com);}



    }

    public boolean getStatus(){
        return this.status;
    }


}
