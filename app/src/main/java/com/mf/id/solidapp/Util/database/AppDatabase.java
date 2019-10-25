package com.mf.id.solidapp.Util.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.Util.ResponseDataModel;

@Database(entities = {ResponseDataModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance = null;
    public abstract DataDao userDao();

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,AppDatabase.class,context.getResources().getString(R.string.databaseName)).build();
        }
        return instance;
    }
}

