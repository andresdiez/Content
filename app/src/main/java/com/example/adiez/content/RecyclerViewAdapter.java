package com.example.adiez.content;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static List<Message> itemData;
    private static ListFragment activity;

    public RecyclerViewAdapter(List<Message> itemData, ListFragment activity){
        this.itemData=itemData;
        this.activity=activity;

    }

    public void setNewList(List<Message> messages) {
        this.itemData=messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        public TextView title;
        public TextView message;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            title = (TextView) itemLayoutView.findViewById(R.id.textView2);
            message = (TextView) itemLayoutView.findViewById(R.id.textView);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putString("title", itemData.get(getAdapterPosition()).title);
            args.putString("message", itemData.get(getAdapterPosition()).message);
            DetailFragment detailFragment=new DetailFragment();
            detailFragment.setArguments(args);
            activity.getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_layout, detailFragment , "tag")
                    .addToBackStack(null)
                    .commit();

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

        holder.title.setText(itemData.get(position).title);
        holder.message.setText(itemData.get(position).message);

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }



}
