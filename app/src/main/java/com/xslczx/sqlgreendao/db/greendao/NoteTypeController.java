package com.xslczx.sqlgreendao.db.greendao;

import com.xslczx.sqlgreendao.db.greendao.controller.DbController;
import com.xslczx.sqlgreendao.db.greendao.entity.NoteType;

import java.io.File;

public class NoteTypeController extends DbController<NoteType> {

    public NoteTypeController() {
        this(null);
    }

    public NoteTypeController(File dbFile) {
        super(dbFile);
    }
}
