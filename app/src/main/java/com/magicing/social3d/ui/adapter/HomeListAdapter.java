package com.magicing.social3d.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicing.social3d.R;
import com.magicing.social3d.model.bean.Note;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/03/28.
 */
public class HomeListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Note> mNoteList;

    public HomeListAdapter(Context context, List<Note> mNoteList){
        this.mContext = context;
        this.mNoteList = mNoteList;
    }


    public void setmNoteList(List<Note> mNoteList) {
        this.mNoteList = mNoteList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_note, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder  = (ViewHolder) viewHolder;
        Note note = mNoteList.get(position);

        String url =  note.getDomain() + note.getPath() + "1.jpg";
        Picasso.with(mContext).load(url).error(R.mipmap.pic_test).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }


    public final class ViewHolder extends RecyclerView.ViewHolder {
         ImageView img;
         TextView content;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            content = (TextView) v.findViewById(R.id.content);
            img = (ImageView) v.findViewById(R.id.img);
        }


    }

}
