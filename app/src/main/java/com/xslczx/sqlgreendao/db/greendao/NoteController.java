package com.xslczx.sqlgreendao.db.greendao;

import com.xslczx.sqlgreendao.db.greendao.controller.DbController;
import com.xslczx.sqlgreendao.db.greendao.entity.Note;

import java.io.File;

public class NoteController extends DbController<Note> {

    public NoteController() {
        this(null);
    }

    public NoteController(File dbFile) {
        super(dbFile);
    }
}
