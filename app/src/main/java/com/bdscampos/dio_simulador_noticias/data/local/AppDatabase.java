package com.bdscampos.dio_simulador_noticias.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bdscampos.dio_simulador_noticias.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDAO newsDao();
}