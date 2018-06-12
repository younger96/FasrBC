package com.example.latter_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * 数据库管理器，抽离出数据库的修改功能
 */
public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager(){

    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
