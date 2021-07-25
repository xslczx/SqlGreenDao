package com.xslczx.sqlgreendao.db.greendao.entity;

import com.xslczx.sqlgreendao.db.greendao.converter.NoteTypeConverter;
import com.xslczx.sqlgreendao.db.greendao.converter.TypeEnum;
import org.greenrobot.greendao.annotation.*;

import java.util.Date;

@Entity
public class NoteType {
    @Transient
    public Boolean tmpFlag;
    @Id(autoincrement = true)
    private Long id;
    @Unique
    @NotNull
    private String name;
    @Unique
    @NotNull
    private String typeId;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date updatedAt;
    private Date syncAt;
    private int colorIndex;
    private int sort;
    private int status;
    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private TypeEnum typeEnum;
    @Generated(hash = 1810900533)
    public NoteType(Long id, @NotNull String name, @NotNull String typeId,
            @NotNull Date createdAt, @NotNull Date updatedAt, Date syncAt,
            int colorIndex, int sort, int status, TypeEnum typeEnum) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.syncAt = syncAt;
        this.colorIndex = colorIndex;
        this.sort = sort;
        this.status = status;
        this.typeEnum = typeEnum;
    }
    @Generated(hash = 1549737010)
    public NoteType() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTypeId() {
        return this.typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
    public int getColorIndex() {
        return this.colorIndex;
    }
    public void setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
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
    public TypeEnum getTypeEnum() {
        return this.typeEnum;
    }
    public void setTypeEnum(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
