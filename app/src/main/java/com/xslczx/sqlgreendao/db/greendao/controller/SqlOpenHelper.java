package com.xslczx.sqlgreendao.db.greendao.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.xslczx.sqlgreendao.db.greendao.gen.DaoMaster;
import com.xslczx.sqlgreendao.db.greendao.gen.DaoSession;
import com.xslczx.sqlgreendao.db.greendao.gen.NoteDao;
import com.xslczx.sqlgreendao.db.greendao.gen.NoteTypeDao;
import org.greenrobot.greendao.database.Database;

import java.io.File;

public class SqlOpenHelper extends DaoMaster.OpenHelper {

    public SqlOpenHelper(@Nullable File file) {
        super(new DbContext(file == null ? null : file.getParentFile()), file == null ? "notesql.db" : file.getName(), null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, NoteDao.class, NoteTypeDao.class);
    }

    @NonNull
    public DaoSession newSession() {
        return new DaoMaster(getWritableDatabase()).newSession();
    }
}
