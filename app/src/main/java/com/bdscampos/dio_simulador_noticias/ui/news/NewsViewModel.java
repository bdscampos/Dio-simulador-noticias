package com.bdscampos.dio_simulador_noticias.ui.news;

import androidx.annotation.NonNull;
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

    public enum State{
        DOING, DONE, ERROR
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();
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
        state.setValue(State.DOING);
        service.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()){
                    state.setValue(State.DONE);
                    news.setValue(response.body());
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                t.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
    public LiveData<State> getState() { return this.state; }
}