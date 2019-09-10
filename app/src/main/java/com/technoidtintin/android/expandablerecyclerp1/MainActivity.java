package com.technoidtintin.android.expandablerecyclerp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG,"Welcome");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovieItems().observe(this, new Observer<List<listItem>>() {
            @Override
            public void onChanged(List<listItem> listItems) {
                 if (listItems != null){

                     Log.e(TAG,"list is full");
                     movieAdapter.setListItems(listItems);
                 }else {
                     Toast.makeText(getApplicationContext(),"list id empty",Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
}
