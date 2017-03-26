package com.farac.mp3tag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v22Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;


public class TagEditor extends AppCompatActivity {
    Mp3File mp3;
    String filename;
    ID3v2 tag;
    EditText title;
    EditText artist;
    EditText album;
    EditText year;
    EditText no;
    Button commit;
    Boolean a,b;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_editor);
        Intent intent =getIntent();
        final String file_clicked= intent.getStringExtra("file_clicked");
        Toast.makeText(this, ""+file_clicked, Toast.LENGTH_SHORT).show();
        title =(EditText)findViewById(R.id.track_title);
        artist =(EditText)findViewById(R.id.artist_name);
        album =(EditText)findViewById(R.id.album_name);
        year =(EditText)findViewById(R.id.year);
        no =(EditText)findViewById(R.id.track_number);
        commit=(Button) findViewById(R.id.commit_tags);

        

        try {
            mp3 = new Mp3File(file_clicked);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        filename= new File(file_clicked).getName();
        setTitle(filename);
        if (mp3.hasId3v2Tag()){
            tag = mp3.getId3v2Tag();
        }
        else{
            tag= new ID3v22Tag();
            mp3.setId3v2Tag(tag);
        }

        title.setText(tag.getTitle());
        artist.setText(tag.getArtist());
        album.setText(tag.getAlbum());
        year.setText(tag.getYear());
        no.setText(tag.getTrack());


        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag.setTitle(title.getText().toString().trim());
                tag.setArtist(artist.getText().toString().trim());
                tag.setAlbum(album.getText().toString().trim());
                tag.setYear(year.getText().toString().trim());
                tag.setTrack(no.getText().toString().trim());
                try {
                    mp3.save(file_clicked+".new");
                } catch (IOException | NotSupportedException e) {
                    e.printStackTrace();
                    
                }
                File old = new File(file_clicked);
                File edited= new File(file_clicked+".new");
                a= old.delete();
                b= edited.renameTo(old);
                if(a&b) {
                    Toast.makeText(TagEditor.this, "Saved changes successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(TagEditor.this, "Operation failed!", Toast.LENGTH_SHORT).show();
                }


                // f.delete();
               // f= new File(file_clicked +".new");
               // f.renameTo(new File(file_clicked));


            }
        });



    }

}

