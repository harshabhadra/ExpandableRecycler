package com.technoidtintin.android.expandablerecyclerp1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private Repository repository;

    public MovieViewModel() {

        repository = Repository.getInstance();
    }

    public LiveData<List<listItem>>getMovieItems(){
        return repository.getMovieList();
    }
}
