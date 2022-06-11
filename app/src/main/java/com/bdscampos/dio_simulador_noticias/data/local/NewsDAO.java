package com.bdscampos.dio_simulador_noticias.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bdscampos.dio_simulador_noticias.domain.News;

import java.util.List;

@Dao
public interface NewsDAO {

    @Query("SELECT * FROM news WHERE favorite = 1")
    List<News> loadFavoriteNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News favoritedNews);
}
