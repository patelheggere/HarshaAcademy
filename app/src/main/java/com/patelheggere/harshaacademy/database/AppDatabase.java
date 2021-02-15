package com.patelheggere.harshaacademy.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.patelheggere.harshaacademy.model.MCQQuestionModel;


@Database(entities = {MCQQuestionModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao taskDao();
}