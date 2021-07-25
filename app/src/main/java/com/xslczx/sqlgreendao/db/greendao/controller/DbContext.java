package com.xslczx.sqlgreendao.db.greendao.controller;

import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import com.xslczx.App;

import java.io.File;
import java.io.IOException;

public class DbContext extends ContextWrapper {

    private final File mFileDir;

    public DbContext(@Nullable File fileDir) {
        super(App.getContext());
        this.mFileDir = fileDir;
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     *
     * @param dbName notesql.db
     */
    @Override
    public File getDatabasePath(String dbName) {
        if (mFileDir == null) return super.getDatabasePath(dbName);
        if (!mFileDir.exists()) {
            boolean mkdirs = mFileDir.mkdirs();
            if (!mkdirs) {
                return super.getDatabasePath(dbName);
            }
        }
        boolean isFileCreateSuccess = false;
        File dbFile = new File(mFileDir, dbName);
        if (!dbFile.exists()) {
            try {
                isFileCreateSuccess = dbFile.createNewFile();
            } catch (IOException ignore) {
            }
        } else {
            isFileCreateSuccess = true;
        }
        if (isFileCreateSuccess) {
            return dbFile;
        }
        return super.getDatabasePath(dbName);
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }
}
