package com.magicing.social3d.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.magicing.social3d.R;
import com.magicing.social3d.model.bean.Note;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/03/28.
 */
public class MainListViewAdapter extends BaseAdapter {

    private Activity mContext;
    private List<Note> mNoteList;

    public MainListViewAdapter(Activity context, List<Note> mNoteList){
        this.mContext = context;
        this.mNoteList = mNoteList;
    }


    public void setmNoteList(List<Note> mNoteList) {
        this.mNoteList = mNoteList;
    }

    @Override
    public int getCount() {
        return mNoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_note,null);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            holder.content = (TextView)convertView.findViewById(R.id.content);

            convertView.setTag(holder);//绑定ViewHolder对象
        } else{
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
        }

        Note note = mNoteList.get(position);

        String url =  note.getDomain() + note.getPath() + "1.jpg";
        Picasso.with(mContext).load(url).error(R.mipmap.pic_test).into(holder.img);

        holder.content.setText(note.getContent());


        return convertView;
    }

    final class ViewHolder {
        ImageView img;
        TextView content;
        TextView code;
        ProgressBar mProgressBar;
        LinearLayout lay_load;
    }
}
