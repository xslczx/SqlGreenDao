package com.xslczx.sqlgreendao.db.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xslczx.sqlgreendao.db.greendao.converter.NoteTypeConverter;
import com.xslczx.sqlgreendao.db.greendao.converter.TypeEnum;

import com.xslczx.sqlgreendao.db.greendao.entity.NoteType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTE_TYPE".
*/
public class NoteTypeDao extends AbstractDao<NoteType, Long> {

    public static final String TABLENAME = "NOTE_TYPE";

    /**
     * Properties of entity NoteType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property TypeId = new Property(2, String.class, "typeId", false, "TYPE_ID");
        public final static Property CreatedAt = new Property(3, java.util.Date.class, "createdAt", false, "CREATED_AT");
        public final static Property UpdatedAt = new Property(4, java.util.Date.class, "updatedAt", false, "UPDATED_AT");
        public final static Property SyncAt = new Property(5, java.util.Date.class, "syncAt", false, "SYNC_AT");
        public final static Property ColorIndex = new Property(6, int.class, "colorIndex", false, "COLOR_INDEX");
        public final static Property Sort = new Property(7, int.class, "sort", false, "SORT");
        public final static Property Status = new Property(8, int.class, "status", false, "STATUS");
        public final static Property TypeEnum = new Property(9, String.class, "typeEnum", false, "TYPE_ENUM");
    }

    private final NoteTypeConverter typeEnumConverter = new NoteTypeConverter();

    public NoteTypeDao(DaoConfig config) {
        super(config);
    }
    
    public NoteTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTE_TYPE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL UNIQUE ," + // 1: name
                "\"TYPE_ID\" TEXT NOT NULL UNIQUE ," + // 2: typeId
                "\"CREATED_AT\" INTEGER NOT NULL ," + // 3: createdAt
                "\"UPDATED_AT\" INTEGER NOT NULL ," + // 4: updatedAt
                "\"SYNC_AT\" INTEGER," + // 5: syncAt
                "\"COLOR_INDEX\" INTEGER NOT NULL ," + // 6: colorIndex
                "\"SORT\" INTEGER NOT NULL ," + // 7: sort
                "\"STATUS\" INTEGER NOT NULL ," + // 8: status
                "\"TYPE_ENUM\" TEXT);"); // 9: typeEnum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTE_TYPE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NoteType entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getTypeId());
        stmt.bindLong(4, entity.getCreatedAt().getTime());
        stmt.bindLong(5, entity.getUpdatedAt().getTime());
 
        java.util.Date syncAt = entity.getSyncAt();
        if (syncAt != null) {
            stmt.bindLong(6, syncAt.getTime());
        }
        stmt.bindLong(7, entity.getColorIndex());
        stmt.bindLong(8, entity.getSort());
        stmt.bindLong(9, entity.getStatus());
 
        TypeEnum typeEnum = entity.getTypeEnum();
        if (typeEnum != null) {
            stmt.bindString(10, typeEnumConverter.convertToDatabaseValue(typeEnum));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NoteType entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getTypeId());
        stmt.bindLong(4, entity.getCreatedAt().getTime());
        stmt.bindLong(5, entity.getUpdatedAt().getTime());
 
        java.util.Date syncAt = entity.getSyncAt();
        if (syncAt != null) {
            stmt.bindLong(6, syncAt.getTime());
        }
        stmt.bindLong(7, entity.getColorIndex());
        stmt.bindLong(8, entity.getSort());
        stmt.bindLong(9, entity.getStatus());
 
        TypeEnum typeEnum = entity.getTypeEnum();
        if (typeEnum != null) {
            stmt.bindString(10, typeEnumConverter.convertToDatabaseValue(typeEnum));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NoteType readEntity(Cursor cursor, int offset) {
        NoteType entity = new NoteType( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // typeId
            new java.util.Date(cursor.getLong(offset + 3)), // createdAt
            new java.util.Date(cursor.getLong(offset + 4)), // updatedAt
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // syncAt
            cursor.getInt(offset + 6), // colorIndex
            cursor.getInt(offset + 7), // sort
            cursor.getInt(offset + 8), // status
            cursor.isNull(offset + 9) ? null : typeEnumConverter.convertToEntityProperty(cursor.getString(offset + 9)) // typeEnum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NoteType entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setTypeId(cursor.getString(offset + 2));
        entity.setCreatedAt(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setUpdatedAt(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setSyncAt(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setColorIndex(cursor.getInt(offset + 6));
        entity.setSort(cursor.getInt(offset + 7));
        entity.setStatus(cursor.getInt(offset + 8));
        entity.setTypeEnum(cursor.isNull(offset + 9) ? null : typeEnumConverter.convertToEntityProperty(cursor.getString(offset + 9)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NoteType entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NoteType entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NoteType entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
