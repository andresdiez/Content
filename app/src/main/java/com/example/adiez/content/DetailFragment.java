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
import android.widget.Toast;

import com.example.adiez.content.model.Handler;
import com.example.adiez.content.model.Message;
import com.example.adiez.content.model.Model;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements Handler{


    DetailFragmentPresenter presenter;
    private int i;


    @Bind(R.id.button2) Button saveButton;
    @Bind(R.id.textView3) TextView title;
    @Bind(R.id.textView4) TextView message;
    @Bind(R.id.editText3) EditText eTitle;
    @Bind(R.id.editText4) EditText eMessage;
    private Model model;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter=new DetailFragmentPresenter(this,model);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, rootView);
        presenter.displayMessage(i);
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

    @Override
    public void registerModel(Model model) { this.model = model; }


    public void editMessage(){
        eTitle.setText(title.getText().toString());
        eMessage.setText(message.getText().toString());

        getActivity().findViewById(R.id.relative1).setVisibility(View.VISIBLE);
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        presenter.onDataChange(i,
                                eTitle.getText().toString(),
                                eMessage.getText().toString());

                        getActivity().findViewById(R.id.relative1).setVisibility(View.GONE);

                    }
                }
        );


    }



    @Override
    public void dataDidLoad(List<Message> messages) {

    }

    private void deleteMessage() {
        new AlertDialog.Builder(this.getActivity())
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.deleteMessage(i,getActivity());
                        //.onBackPressed();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
