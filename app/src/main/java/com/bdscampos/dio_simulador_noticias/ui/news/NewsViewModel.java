package com.bdscampos.dio_simulador_noticias.ui.news;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bdscampos.dio_simulador_noticias.data.remote.FootballNewsApi;
import com.bdscampos.dio_simulador_noticias.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final FootballNewsApi service;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bdscampos.github.io/dio-simulador-noticias-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FootballNewsApi.class);
        this.findNews();
    }

    private void findNews() {
        service.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    news.setValue(response.body());
                } else {
                    //TODO Pensar em estratégia de tratamento de erros
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO Pensar em estratégia de tratamento de erros
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}