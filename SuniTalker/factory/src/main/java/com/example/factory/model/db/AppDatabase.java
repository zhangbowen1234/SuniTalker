package com.example.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by bowen on 2018/3/13.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}
