package com.farac.mp3tag;


import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileListing extends AppCompatActivity {
    private static final String TAG = "FileListingTag";
    private File selected;
    private File root = Environment.getExternalStorageDirectory();
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
        setTitle("Select an MP3 file");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });


        if (ActivityCompat.checkSelfPermission(FileListing.this,
                permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (FileListing.this, new String[]{permission.READ_EXTERNAL_STORAGE}, 1);
            Log.v(TAG, " upa u read if");
        }

        if (ActivityCompat.checkSelfPermission(FileListing.this,
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (FileListing.this, new String[]{permission.WRITE_EXTERNAL_STORAGE}, 2);
            Log.v(TAG, " upa u write if");
        }
        //if (!current_folder.exists()) {
            current_folder = root;
       // }

        final FileOperationsModel FOM;
        FOM = new FileOperationsModel();
        FOM.sendData(current_folder);
        dir_name = Arrays.asList(FOM.getFileName());
        icon_img = Arrays.asList(FOM.getIm_res());
        path = Arrays.asList(FOM.getPathData());


        Log.v(TAG, " ispa iz oba/prisa ifove");

        final FileListingAdapter adapter = new FileListingAdapter(this, dir_name, icon_img, path);
        list = (ListView) findViewById(R.id.input);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FileListing.this, "Klika si broj:" + i, Toast.LENGTH_SHORT).show();

                String[] cpath =FOM.getPathData();
                File temp= new File (cpath[i]);
                if(temp.isDirectory()) {
                    current_folder = temp;
                    Toast.makeText(FileListing.this, "" + current_folder, Toast.LENGTH_LONG).show();
                    FOM.sendData(current_folder);
                    dir_name = Arrays.asList(FOM.getFileName());
                    icon_img = Arrays.asList(FOM.getIm_res());
                    path = Arrays.asList(FOM.getPathData());
                    final FileListingAdapter adapter = new FileListingAdapter(FileListing.this, dir_name, icon_img, path);
                    list = (ListView) findViewById(R.id.input);
                    list.setAdapter(adapter);
                }
                else if(FOM.isMP3(temp)){
                    Toast.makeText(FileListing.this, "klika si mp3", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FileListing.this, TagEditor.class);
                    intent.putExtra("file_clicked", temp.getAbsolutePath());
                    FileListing.this.startActivity(intent);
                }
                else {
                    Toast.makeText(FileListing.this, "Only MP3 files are supported!", Toast.LENGTH_SHORT).show();
                }


            }
        });


}


    public static Intent newIntent(Context packageContext) {
        Intent i= new Intent(packageContext, FileListing.class);
        return i;
    }







}
