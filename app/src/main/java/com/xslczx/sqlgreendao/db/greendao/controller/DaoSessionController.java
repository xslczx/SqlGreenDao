package com.xslczx.sqlgreendao.db.greendao.controller;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.xslczx.sqlgreendao.db.greendao.gen.DaoSession;

import java.io.File;

public class DaoSessionController {
    private static DaoSessionController sController;
    private SqlOpenHelper mSqlOpenHelper;
    private DaoSession mDaoSession;

    private DaoSessionController() {
    }

    public static DaoSessionController getController() {
        if (sController == null) {
            synchronized (DaoSessionController.class) {
                if (sController == null) {
                    sController = new DaoSessionController();
                }
            }
        }
        return sController;
    }

    /**
     * @param file 数据库文件 ，null 默认为database目录下的notesql.db
     * @return DaoSession
     */
    @NonNull
    public DaoSession getDaoSession(@Nullable File file) {
        if (mDaoSession == null) {
            if (mSqlOpenHelper == null) {
                mSqlOpenHelper = new SqlOpenHelper(file);
            }
            mDaoSession = mSqlOpenHelper.newSession();
        }
        return mDaoSession;
    }

    /**
     * 关闭DaoSession
     */
    private void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    /**
     * 关闭Helper
     */
    private void closeHelper() {
        if (mSqlOpenHelper != null) {
            mSqlOpenHelper.close();
            mSqlOpenHelper = null;
        }
    }

    /**
     * 关闭所有的操作
     */
    public void closeConnection() {
        closeDaoSession();
        closeHelper();
    }
}
