package com.nguyenducnha.doan_nuocngot;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NuocNgot.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NuocNgotDao nuocNgotDao();
    private static volatile AppDatabase INSTANCE=null;
    public static AppDatabase getInstance(Context context){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,AppDatabase.class,"qlNuocNgot").build();
                }
            }
        }
        return INSTANCE;
    }
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService executor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);
}
