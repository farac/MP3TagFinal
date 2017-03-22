package com.farac.mp3tag;

import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileListing extends AppCompatActivity {

    private static final String TAG="FileListingTag";


    private File root= Environment.getExternalStorageDirectory();
    private File current_folder;



    ListView list;
    List<String> dir_name;

    List<Integer> icon_img;

    List<String> path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "snackbar mi moze popusit kurac aka izbrisi me", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }


        });





        if (ActivityCompat.checkSelfPermission(FileListing.this,
                permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions
                    (FileListing.this,new String[]{permission.READ_EXTERNAL_STORAGE},1);
            Log.v(TAG," upa u read if");
        }

        if (ActivityCompat.checkSelfPermission(FileListing.this,
                permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions
                    (FileListing.this,new String[]{permission.WRITE_EXTERNAL_STORAGE},2);
            Log.v(TAG," upa u write if");
        }

        current_folder=root;
        File[] files_list=current_folder.listFiles();
        String[] file_name = new String[files_list.length];
        Integer[] icon_im= new Integer[files_list.length];
        String[] fpath = new String[files_list.length];

        Arrays.sort(files_list);

        for(int i=0;i<file_name.length;i++){
            file_name[i]= files_list[i].getName();
            fpath[i]=files_list[i].getAbsolutePath();

            String ext =files_list[i].getName().substring(files_list[i].getName().lastIndexOf(".")+1,files_list[i].getName().length());
            File tf=new File(files_list[i].getAbsolutePath());
            Log.e(TAG," ekstenzija je " +ext);
            if(tf.isDirectory()){
                icon_im[i]=R.drawable.ic_folder;
            }
            else if(Objects.equals(ext, "mp3")) {
                icon_im[i] = R.drawable.ic_music_note;
            }
            else icon_im[i]=R.drawable.ic_insert_drive_file;
        }

        dir_name= Arrays.asList(file_name);
        icon_img=Arrays.asList(icon_im);
        path=Arrays.asList(fpath);

        Log.v(TAG," ispa iz oba/prisa ifove");
        FileListingAdapter adapter = new FileListingAdapter(this, dir_name, icon_img, path);
        list=(ListView)findViewById(R.id.files_list);
        list.setAdapter(adapter);
    }

    public static Intent newIntent(Context packageContext) {
        Intent i= new Intent(packageContext, FileListing.class);
        return i;
    }

}
