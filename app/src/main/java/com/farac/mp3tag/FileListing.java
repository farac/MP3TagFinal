package com.farac.mp3tag;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import static com.farac.mp3tag.R.id.icon;

public class FileListing extends AppCompatActivity {

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

        FileListingAdapter adapter = new FileListingAdapter(this, dir_name, icon_img, path);
        list=(ListView)findViewById(R.id.files_list);
        list.setAdapter(adapter);
    }

    public static Intent newIntent(Context packageContext) {
        Intent i= new Intent(packageContext, FileListing.class);
        return i;
    }

}
