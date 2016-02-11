package com.example.adiez.content;

import android.app.Fragment;
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

public class DetailFragment extends Fragment implements DetailFragmentPresenter.DetailView{


    DetailFragmentPresenter presenter;
    private int i;
    private boolean dataHasChange =false;

    TextView title;
    TextView message;
    EditText eMessage;
    EditText eTitle;
    Button saveButton;


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

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.detail_fragment, container, Boolean.parseBoolean(null));
        title=(TextView)rootView.findViewById(R.id.textView3);
        message=(TextView)rootView.findViewById(R.id.textView4);
        eTitle=(EditText)rootView.findViewById(R.id.editText3);
        eMessage =(EditText)rootView.findViewById(R.id.editText4);
        saveButton=(Button)rootView.findViewById(R.id.button2);


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
        if (dataHasChange){presenter.saveData();}
    }



}
