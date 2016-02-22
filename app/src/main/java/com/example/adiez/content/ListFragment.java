package com.example.adiez.content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

import static android.view.View.*;


public class  ListFragment extends Fragment implements Handler {





    @Bind(R.id.button) Button button;
    @Bind(R.id.view) RecyclerView recyclerView;
    @Bind(R.id.editText) EditText title;
    @Bind(R.id.editText2) EditText message;

    private RecyclerViewAdapter adapter;
    private ListFragmentPresenter presenter;
    private Model model;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ListFragmentPresenter( this , model );
        presenter.build();
        adapter=new RecyclerViewAdapter(presenter.getList() ,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        ButterKnife.bind(this, rootView);
        LinearLayoutManager llm = new LinearLayoutManager(inflater.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);

        button.setOnClickListener(clickListener);
        message.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    button.performClick();
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }



    @Override
    public void dataDidLoad(List<Message> messages) {
        adapter.setNewList(messages);
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(messages.size() - 1);
    }

    @Override
    public void displayMessage(String t, String m) {

    }

    @Override
    public void registerModel(Model model) {
        this.model = model;
    }


    final OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String strTitle=title.getText().toString();
            String strMessage=message.getText().toString();
            if (strTitle.equals("")||strMessage.equals("")){
                Toast.makeText(getActivity()
                        ,"Title and message must have a body"
                        ,Toast.LENGTH_LONG).show();

            }
            else {
                presenter.addMessage(strTitle, strMessage);
                title.setText("");
                message.setText("");
            }
        }

    };








}



