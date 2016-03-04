package com.example.adiez.content;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Message;

import java.util.HashMap;
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
        public ImageView image;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);



            title = (TextView) itemLayoutView.findViewById(R.id.textView2);
            message = (TextView) itemLayoutView.findViewById(R.id.textView);
            image=(ImageView) itemLayoutView.findViewById(R.id.imageView);
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
                .inflate(R.layout.item_layout, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        String videoUrl=itemData.get(position).getMessage();

        holder.title.setText(itemData.get(position).getTitle());
        holder.message.setText(videoUrl);
        new LoadImg(holder).execute(videoUrl);

//        try {
//
//            holder.image.setImageBitmap(retrieveVideoFrameFromVideo(videoUrl));
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }



    private class LoadImg extends AsyncTask<String,Void,Bitmap>{

        private final ViewHolder holder;

        public LoadImg(ViewHolder holder) {

            this.holder = holder;

        }
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return retrieveVideoFrameFromVideo(params[0]);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            holder.image.setImageBitmap(bitmap);
        }

        private Bitmap retrieveVideoFrameFromVideo(String videoPath)
                throws Throwable
        {
            Bitmap bitmap = null;
            MediaMetadataRetriever mediaMetadataRetriever = null;
            try
            {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                if (Build.VERSION.SDK_INT >= 14)
                    mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
                else
                    mediaMetadataRetriever.setDataSource(videoPath);
                //   mediaMetadataRetriever.setDataSource(videoPath);
                bitmap = mediaMetadataRetriever.getFrameAtTime();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new Throwable(
                        "Exception in retrieveVideoFrameFromVideo(String videoPath)"
                                + e.getMessage());

            }
            finally
            {
                if (mediaMetadataRetriever != null)
                {
                    mediaMetadataRetriever.release();
                }
            }
            return bitmap;
        }


    }



}
