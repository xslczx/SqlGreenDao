package com.xslczx.sqlgreendao.db.greendao.entity;

import com.xslczx.sqlgreendao.db.greendao.converter.StringConverter;
import org.greenrobot.greendao.annotation.*;

import java.util.Date;
import java.util.List;

@Entity
public class Note {
    @Transient
    public Boolean tmpFlag;
    @Id(autoincrement = true)
    private Long id;
    @Unique
    @NotNull
    private String noteId;
    @NotNull
    private String typeName;
    private String content;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date updatedAt;
    private Date syncAt;
    private Date remindAt;
    private int sort;
    private int status;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> media;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> typeface;
    @Generated(hash = 1933245499)
    public Note(Long id, @NotNull String noteId, @NotNull String typeName,
            String content, @NotNull Date createdAt, @NotNull Date updatedAt,
            Date syncAt, Date remindAt, int sort, int status, List<String> media,
            List<String> typeface) {
        this.id = id;
        this.noteId = noteId;
        this.typeName = typeName;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.syncAt = syncAt;
        this.remindAt = remindAt;
        this.sort = sort;
        this.status = status;
        this.media = media;
        this.typeface = typeface;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNoteId() {
        return this.noteId;
    }
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Date getSyncAt() {
        return this.syncAt;
    }
    public void setSyncAt(Date syncAt) {
        this.syncAt = syncAt;
    }
    public Date getRemindAt() {
        return this.remindAt;
    }
    public void setRemindAt(Date remindAt) {
        this.remindAt = remindAt;
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public List<String> getMedia() {
        return this.media;
    }
    public void setMedia(List<String> media) {
        this.media = media;
    }
    public List<String> getTypeface() {
        return this.typeface;
    }
    public void setTypeface(List<String> typeface) {
        this.typeface = typeface;
    }
}
