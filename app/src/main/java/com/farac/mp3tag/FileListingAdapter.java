package com.farac.mp3tag;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bruno on 01/02/2017.
 */

//http://www.androidinterview.com/android-custom-listview-with-image-and-text-using-arrayadapter/

public class FileListingAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> dir_name;
    private final List<Integer> icon_img;
    private final List<String> path;

    public FileListingAdapter (Activity context, List<String> dir_name,List<Integer> icon_img, List<String> path){
        super(context,R.layout.file_list,dir_name);

        this.context=context;
        this.dir_name=dir_name;
        this.icon_img=icon_img;
        this.path=path;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.file_list, null, true);

        TextView directory =(TextView) rowView.findViewById(R.id.dir_name);
        ImageView icon = (ImageView) rowView.findViewById(R.id.list_icon);
        TextView full_path = (TextView) rowView.findViewById(R.id.full_path);

        directory.setText(dir_name.get(position));
        icon.setImageResource(icon_img.get(position));
        full_path.setText(path.get(position));

        return rowView;
    }


    }

