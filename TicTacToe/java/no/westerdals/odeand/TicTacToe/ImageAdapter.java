package no.westerdals.odeand.TicTacToe;
// Created by Andreas Ødegaard on 13.03.2017.

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mThumbsIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbsIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.grid_layout, parent, false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.imageicon);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageView imageView = holder.img;
        imageView.setImageResource(mThumbsIds[position]);

        return convertView;
    }


    public Integer[] mThumbsIds = {
            R.drawable.frame, R.drawable.frame,
            R.drawable.frame, R.drawable.frame,
            R.drawable.frame, R.drawable.frame,
            R.drawable.frame, R.drawable.frame,
            R.drawable.frame
    };


}

class ViewHolder {
    ImageView img;
}
