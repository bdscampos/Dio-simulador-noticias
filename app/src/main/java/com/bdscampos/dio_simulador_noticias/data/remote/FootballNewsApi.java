package com.bdscampos.dio_simulador_noticias.data.remote;

import com.bdscampos.dio_simulador_noticias.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballNewsApi {

    @GET("news.json")
    Call<List<News>> getNews();
}
