package com.example.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by c8469 on 2016/12/22.
 */

public class OrmliteOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_USER = "db_user.db";
    private static final int DB_VERSION = 1;

    private static OrmliteOpenHelper instance;
    //dao :  database access object   database manager
    private static Dao<User,Long> dao;


    public static Dao<User,Long> getDao(Context context) throws SQLException {

        return getInstance(context).getDao(User.class);

    }

    private OrmliteOpenHelper(Context context) {
        super(context, DB_USER, null, DB_VERSION);
    }

    public static OrmliteOpenHelper getInstance(Context context){

        if(instance == null){

            instance = new OrmliteOpenHelper(context);

        }

        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTableIfNotExists(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        if(newVersion > oldVersion){

            try {
                TableUtils.dropTable(connectionSource,User.class,true);
                onCreate(database,connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
