package com.bdscampos.dio_simulador_noticias.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.bdscampos.dio_simulador_noticias.MainActivity;
import com.bdscampos.dio_simulador_noticias.data.local.AppDatabase;
import com.bdscampos.dio_simulador_noticias.databinding.FragmentNewsBinding;
import com.bdscampos.dio_simulador_noticias.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, favoritedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                AsyncTask.execute(() -> {
                    if (activity != null) {
                        activity.getDb().newsDao().save(favoritedNews);
                    }
                });
            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state){
                case DOING:
                    //TODO iniciar swiperefreshlayout (loading)
                    break;
                case DONE:
                    //TODO Finalizar swiperefreshlayout
                    break;
                case ERROR:
                    //TODO Finalizar swiperefreshlayout e exibir erro
                    break;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}