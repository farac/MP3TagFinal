package com.farac.mp3tag;


import java.io.File;

/**
 * Created by Farac on 16.2.2017..
 */

public class FileOperationsModel {
    private String Path="";
    private int Icon_type=0; //1=dir, 2=other file, 3=mp3
    private String Name="";

    public String getPath() {
        return Path;
    }

    public void setPath(File path) {
        Path = path.getAbsolutePath();
    }

    public int getIcon_type() {
        return Icon_type;
    }

    public void setIcon_type(File icon_type) {
        if (icon_type.isDirectory()) {
            Icon_type = 1;
        } else {
            String[] split = icon_type.getName().split(".");
            if (split[1] =="mp3"){
                Icon_type=3;
            }
            else{
                Icon_type=2;
            }
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(File name) {
        Name = name.getName();
    }
}







