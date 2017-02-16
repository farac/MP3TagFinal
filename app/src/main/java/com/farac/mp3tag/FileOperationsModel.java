package com.farac.mp3tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Farac on 16.2.2017..
 */

public class FileOperationsModel {
    private File mCurrentDir;
    private File mPreviousDir;
    private Stack<File> mHistoryStack;

    public FileOperationsModel(){
        generate();
    }
    private void generate() {
        mHistoryStack = new Stack<>();
    }

    public File getmCurrentDir() {
        return mCurrentDir;
    }

    public void setmCurrentDir(File mCurrentDir) {
        this.mCurrentDir = mCurrentDir;
    }

    public boolean hasmPreviousDir(){
        return !mHistoryStack.isEmpty();
    }

    public File getmPreviousDir(){
        return mHistoryStack.pop();
    }

    public void setmPreviousDir(File mPreviousDir){
        this.mPreviousDir=mPreviousDir;
        mHistoryStack.add(mPreviousDir);
    }
    public List<File> getAllFiles(File f){
        File[] allFiles =f.listFiles();
        List<File> dirs = new ArrayList<>();
        List<File> files = new ArrayList<>();

        for (File file : allFiles){
            if (file.isDirectory()) {
                dirs.add(file);
            }else{
                files.add(file);
                }
            }
        }

    }







