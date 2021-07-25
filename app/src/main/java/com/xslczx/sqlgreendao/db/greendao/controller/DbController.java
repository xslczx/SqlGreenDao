package com.xslczx.sqlgreendao.db.greendao.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.xslczx.sqlgreendao.db.greendao.gen.DaoSession;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public abstract class DbController<T> {
    protected final Class<T> entityClass;
    protected File mDbFile;
    protected OnQueryAllListener<T> mOnQueryAllListener;
    protected OnUpdateListener<T> mOnUpdateListener;
    protected OnInsertListener<T> mOnInsertListener;
    protected OnQueryUniqueListener<T> mOnQueryUniqueListener;
    protected OnDeleteListener<T> mOnDeleteListener;

    public DbController(File dbFile) {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) Objects.requireNonNull(type)).getActualTypeArguments()[0];
        //noinspection unchecked
        this.entityClass = (Class<T>) trueType;
        this.mDbFile = dbFile;
    }

    @NonNull
    public DbController<T> setOnQueryAllListener(@Nullable OnQueryAllListener<T> onQueryAllListener) {
        this.mOnQueryAllListener = onQueryAllListener;
        return this;
    }

    @NonNull
    public DbController<T> setOnUpdateListener(@Nullable OnUpdateListener<T> onUpdateListener) {
        this.mOnUpdateListener = onUpdateListener;
        return this;
    }

    @NonNull
    public DbController<T> setOnInsertListener(@Nullable OnInsertListener<T> onInsertListener) {
        this.mOnInsertListener = onInsertListener;
        return this;
    }

    @NonNull
    public DbController<T> setOnQueryUniqueListener(@Nullable OnQueryUniqueListener<T> onQueryUniqueListener) {
        this.mOnQueryUniqueListener = onQueryUniqueListener;
        return this;
    }

    @NonNull
    public DbController<T> setOnDeleteListener(@Nullable OnDeleteListener<T> onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
        return this;
    }

    @NonNull
    public AbstractDao<T, Long> getDao() {
        return (AbstractDao<T, Long>) getDaoSession().getDao(entityClass);
    }

    public DbController<T> setDbFile(@Nullable File dbFile) {
        DaoSessionController.getController().closeConnection();
        mDbFile = dbFile;
        return this;
    }

    @NonNull
    public DaoSession getDaoSession() {
        return DaoSessionController.getController().getDaoSession(mDbFile);
    }

    @NonNull
    public QueryBuilder<T> newQueryBuilder() {
        return getDao().queryBuilder();
    }

    /**
     * 条件查询数据
     */
    @SuppressWarnings("unchecked")
    public void queryUnique(@Nullable Object tag, @NonNull WhereCondition whereCondition) {
        DaoSession daoSession = getDaoSession();
        AsyncSession asyncSession = daoSession.startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnQueryUniqueListener != null) {
                mOnQueryUniqueListener.onQueryUnique(tag, (T) operation.getResult());
            } else if (mOnQueryUniqueListener != null) {
                mOnQueryUniqueListener.onQueryUnique(tag, null);
            }
        });
        Query<T> query = daoSession.queryBuilder(entityClass).where(whereCondition).build();
        asyncSession.queryUnique(query);
    }

    /**
     * 批量查询
     */
    @SuppressWarnings("unchecked")
    public void queryAll(@Nullable Object tag, @Nullable Query<T> query) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            List<T> result = (List<T>) operation.getResult();
            if (mOnQueryAllListener != null) {
                mOnQueryAllListener.onQueryAllBatch(tag, result);
            }
        });
        if (query == null) {
            asyncSession.loadAll(entityClass);
        } else {
            asyncSession.queryList(query);
        }
    }

    /**
     * 删除
     */
    public void deleteSingle(Object tag, T entry) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, true);
            } else if (mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, false);
            }
        });
        asyncSession.runInTx(() -> asyncSession.delete(entry));
    }

    /**
     * 批量删除
     */
    public void deleteBatch(Object tag, final List<T> entities) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, true);
            } else if (mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, false);
            }
        });
        asyncSession.deleteInTx(entityClass, entities);
    }

    /**
     * 根据Id单个删除
     */
    public void deleteByIdSingle(Object tag, long longParams) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, true);
            } else if (mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, false);
            }
        });
        asyncSession.runInTx(() -> asyncSession.deleteByKey(longParams));
    }

    /**
     * 根据Id批量删除
     */
    public void deleteByIdBatch(Object tag, List<Long> longList) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, true);
            } else if (mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, false);
            }
        });
        asyncSession.runInTx(() -> getDao().deleteByKeyInTx(longList));
    }

    /**
     * 删除所有数据
     */
    public void deleteAll(Object tag) {
        final AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, true);
            } else if (mOnDeleteListener != null) {
                mOnDeleteListener.onDelete(tag, false);
            }
        });
        asyncSession.runInTx(() -> asyncSession.deleteAll(entityClass));
    }

    /**
     * 插入一条数据
     */
    public void insertSingle(Object tag, final T entity) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnInsertListener != null) {
                mOnInsertListener.onInsert(tag, true);
            } else if (mOnInsertListener != null) {
                mOnInsertListener.onInsert(tag, false);
            }
        });
        asyncSession.runInTx(() -> asyncSession.insert(entity));
    }

    /**
     * 批量插入
     */
    public void insertBatch(Object tag, final List<T> entities) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnInsertListener != null) {
                mOnInsertListener.onInsert(tag, true);
            } else if (mOnInsertListener != null) {
                Log.wtf(">>>", "error", operation.getThrowable());
                mOnInsertListener.onInsert(tag, false);
            }
        });
        asyncSession.insertOrReplaceInTx(entityClass, entities);
    }

    /**
     * 更新一个数据
     */
    public void updateSingle(Object tag, T entry) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnUpdateListener != null) {
                mOnUpdateListener.onUpdate(tag, true);
            } else if (mOnUpdateListener != null) {
                mOnUpdateListener.onUpdate(tag, false);
            }
        });
        asyncSession.runInTx(() -> asyncSession.update(entry));
    }

    /**
     * 批量更新数据
     */
    public void updateBatch(Object tag, final List<T> entities) {
        AsyncSession asyncSession = getDaoSession().startAsyncSession();
        asyncSession.setListenerMainThread(operation -> {
            if (operation.isCompletedSucessfully() && mOnUpdateListener != null) {
                mOnUpdateListener.onUpdate(tag, true);
            } else if (mOnUpdateListener != null) {
                mOnUpdateListener.onUpdate(tag, false);
            }
        });
        asyncSession.updateInTx(entityClass, entities);
    }

    public interface OnInsertListener<T> {

        void onInsert(Object tag, boolean success);
    }

    public interface OnQueryUniqueListener<T> {

        void onQueryUnique(Object tag, T entry);
    }

    public interface OnDeleteListener<T> {

        void onDelete(Object tag, boolean success);
    }

    public interface OnUpdateListener<T> {
        void onUpdate(Object tag, boolean success);
    }

    public interface OnQueryAllListener<T> {
        void onQueryAllBatch(Object tag, List<T> list);
    }
}
