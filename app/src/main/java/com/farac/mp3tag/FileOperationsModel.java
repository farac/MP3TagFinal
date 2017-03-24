package com.farac.mp3tag;


import android.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Created by Farac on 16.2.2017..
 */

public class FileOperationsModel {

    String[] file_name;
    String[] fpath;
    Integer[] im_res;
    File[] input;

    public void sendData(File f){


        input=f.listFiles();
        file_name = new String[input.length];
        im_res= new Integer[input.length];
        fpath = new String[input.length];

        Arrays.sort(input);

        for(int i=0;i<file_name.length;i++){
            file_name[i]= input[i].getName();
            fpath[i]= input[i].getAbsolutePath();

            String ext = input[i].getName().substring(input[i].getName().lastIndexOf(".")+1, input[i].getName().length());
            File tf=new File(input[i].getAbsolutePath());
            Log.e(TAG," ekstenzija je " +ext);
            if(tf.isDirectory()){
                im_res[i]=R.drawable.ic_folder;
            }
            else if(Objects.equals(ext, "mp3")) {
                im_res[i] = R.drawable.ic_music_note;
            }
            else im_res[i]=R.drawable.ic_insert_drive_file;
        }
    }

    public boolean isMP3(File f){
        String ext = f.getName();
        if(Objects.equals(ext.substring(ext.lastIndexOf(".") + 1), "mp3")) {
            return true;
        }
        else {
            return false;
        }



    }

    public String[] getPathData(){
        return fpath;
    }
    public String[] getFileName(){
        return file_name;
    }
    public Integer[] getIm_res(){
        return im_res;
    }
}







