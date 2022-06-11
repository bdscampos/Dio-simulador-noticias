package com.bdscampos.dio_simulador_noticias.ui.favorites;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdscampos.dio_simulador_noticias.MainActivity;
import com.bdscampos.dio_simulador_noticias.databinding.FragmentFavoritesBinding;
import com.bdscampos.dio_simulador_noticias.domain.News;
import com.bdscampos.dio_simulador_noticias.ui.adapter.NewsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoritedNews();
        return binding.getRoot();
    }

    private void loadFavoritedNews() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            List<News> news = activity.getDb().newsDao().loadFavoriteNews();
            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvNews.setAdapter(new NewsAdapter(news, favoritedNews -> {
                activity.getDb().newsDao().save(favoritedNews);
                loadFavoritedNews();
            }));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}