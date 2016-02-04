package com.example.adiez.content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;



public class  ListFragment extends Fragment implements View.OnClickListener, Presenter.Handler {


    private Presenter presenter;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private EditText title;
    private EditText message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(new Receiver(new Model()),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.view);
        LinearLayoutManager llm = new LinearLayoutManager(inflater.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getMessages();

        Button button=(Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(this);

        title=(EditText) rootView.findViewById(R.id.editText);
        title.setInputType(1);
        message=(EditText) rootView.findViewById(R.id.editText2);
        

        return rootView;
    }




    @Override
    public void onClick(View v) {
        presenter.addMessage(title.getText().toString(), message.getText().toString());
        title.setText("");
        message.setText("");

    }


    @Override
    public void onMessagesReceiver(List<Message> mess) {
        adapter=new RecyclerViewAdapter(mess);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onDataChange() {
        adapter.notifyDataSetChanged();

    }



}



