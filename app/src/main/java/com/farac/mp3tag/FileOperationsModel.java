package com.farac.mp3tag;

import java.io.File;
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





}

