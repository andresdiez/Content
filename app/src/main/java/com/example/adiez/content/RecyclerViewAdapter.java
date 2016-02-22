package com.example.adiez.content;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adiez.content.model.Message;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static List<Message> itemData;
    private static ListFragment activity;

    public RecyclerViewAdapter(List<Message> itemData, ListFragment activity){
        RecyclerViewAdapter.itemData =itemData;
        RecyclerViewAdapter.activity =activity;

    }

    public void setNewList(List<Message> messages) {
        itemData=messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {



        public TextView title;
        public TextView message;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);



            title = (TextView) itemLayoutView.findViewById(R.id.textView2);
            message = (TextView) itemLayoutView.findViewById(R.id.textView);
            itemLayoutView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Communicator com=(Communicator)v.getContext();
                            com.launchFragment(getAdapterPosition());

                        }

                    }
            );

            itemLayoutView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext()
                            , "Title and message must have a body"
                            , Toast.LENGTH_LONG).show();
                    return false;
                }



            });

        }


    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.title.setText(itemData.get(position).getTitle());
        holder.message.setText(itemData.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }



}
