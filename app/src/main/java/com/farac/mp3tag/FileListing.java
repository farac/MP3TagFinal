package com.farac.mp3tag;

import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class FileListing extends AppCompatActivity {


    private static final String TAG="FileListingTag";

    ListView list;
    String[] dir_name={
            "text1",
            "text2",
            "text3"
    };
    Integer[] icon_img={
            R.drawable.ic_action_achievement,
            R.drawable.ic_action_alarm,
            R.drawable.ic_star,
    };

    String[] path={
            "long/path/number1",
            "whatever/thins/is/padding",
            "/aaaaa7aaa7/aaaa"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
