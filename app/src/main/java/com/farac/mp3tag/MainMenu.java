package com.farac.mp3tag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button mButtonEditTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setTitle("Bruno Farac - Maturalni rad");
        mButtonEditTags= (Button) findViewById(R.id.button_edit_files);
        mButtonEditTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = FileListing.newIntent(MainMenu.this);
                startActivity (i);
            }
        });
    }
}


