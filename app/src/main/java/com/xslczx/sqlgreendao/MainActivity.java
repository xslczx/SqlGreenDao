package com.xslczx.sqlgreendao;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.xslczx.sqlgreendao.db.greendao.NoteController;
import com.xslczx.sqlgreendao.db.greendao.entity.Note;
import com.xslczx.sqlgreendao.db.greendao.gen.NoteDao;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {

    private NoteController mNoteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteController = new NoteController();//传入数据库文件或者不传使用默认路径
        mNoteController.setOnQueryAllListener((tag, list) -> {
            Log.d(">>>", "onCreate: queryAll " + list.size());
            if (list.isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    insert();
                }
            }
        }).setOnInsertListener((tag, success) -> Log.d(">>>", "onCreate: insert " + success));
        mNoteController.queryAll(null, mNoteController.newQueryBuilder()
                .where(NoteDao.Properties.Status.ge(1))
                .limit(1000)
                .build());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insert() {
        //添加一百万个数据
        CompletableFuture.supplyAsync(() -> {
            List<Note> list = new ArrayList<>(1000000);
            Date time = Calendar.getInstance().getTime();
            for (int i = 0; i < 1000000; i++) {
                Note note = new Note();
                note.setNoteId(String.valueOf(System.nanoTime()));
                note.setCreatedAt(time);
                note.setUpdatedAt(time);
                note.setTypeName("备忘录");
                note.setContent(UUID.randomUUID().toString());
                note.setStatus(1);
                list.add(note);
            }
            return list;
        }).thenAccept(notes -> mNoteController.insertBatch(null, notes));
    }
}