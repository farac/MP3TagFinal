package com.farac.mp3tag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by bruno on 01/02/2017.
 */

//http://www.androidinterview.com/android-custom-listview-with-image-and-text-using-arrayadapter/

public class FileListingAdapter extends ArrayAdapter<File> {

    private Context mContext;
    private int mResource;
    private List<File> mObjects;

    public FileListingAdapter(Context c, int res, List<File> o){
        super(c, res, o);
        mContext= c;
        mResource=res;
    }
    @Override
    public File getItem(int i){
        return mObjects.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v==null){
            LayoutInflater inflater = (LayoutInflater.from(mContext));
            v= inflater.inflate(mResource, null);
        }
        ImageView iv = (ImageView) v.findViewById(R.id.list_icon);
        TextView nameView = (TextView) v.findViewById(R.id.dir_name);
        TextView detailsView = (TextView) v.findViewById(R.id.full_path);
        File file=getItem(position);

        if (file.isDirectory()){
            iv.setImageResource(R.drawable.ic_folder);
        } else {
            iv.setImageResource(R.drawable.ic_insert_drive_file);
            if(file.length()>0){
                detailsView.setText(String.valueOf(file.length()));
            }
        }

        nameView.setText(file.getName());
        return v;

    }
}




