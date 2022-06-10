package com.bdscampos.dio_simulador_noticias.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bdscampos.dio_simulador_noticias.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        news = new MutableLiveData<>();

        List<News> newsList = new ArrayList<>();
        newsList.add(new News("Grêmio humilha o Internacional no clássico", "Gurias gremistas aplicam 7 x 1 no colorado"));
        newsList.add(new News("Palmeiras e Corinthians ficam no empate", "Corinthians abre 2 x 0, mas Palmeiras busca igualdade"));
        newsList.add(new News("Marta anuncia aposentadoria", "Craque da seleção anunciou que está pendurando as chuteiras"));

        news.setValue(newsList);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}