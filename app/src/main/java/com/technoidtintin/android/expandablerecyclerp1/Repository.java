package com.technoidtintin.android.expandablerecyclerp1;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    private MutableLiveData<List<listItem>> mutableLiveData = new MutableLiveData<>();

    public static Repository getInstance(){
        return new Repository();
    }

    public LiveData<List<listItem>> getMovieList(){

        loadMovieList();
        return mutableLiveData;
    }

    private void loadMovieList() {
        final List<listItem>listItems = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<String>call = api.getMovieList("Enter API KEY here");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body() != null){

                    Log.e(TAG,"response successful: " );
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        JSONArray jsonArray = jsonObject.getJSONArray("results");

                        for (int i = 0; i<jsonArray.length(); i++){

                            JSONObject movieObj = jsonArray.getJSONObject(i);
                            String id = movieObj.getString("id");

                            String posterImage = movieObj.getString("poster_path");
                            String image_url = "http://image.tmdb.org/t/p/w185/" + posterImage;
                            String title = movieObj.getString("title");
                            String overview = movieObj.getString("overview");
                            String realeseDate = movieObj.getString("release_date");
                            String backdrop = movieObj.getString("backdrop_path");
                            String cover = "http://image.tmdb.org/t/p/w185/" + backdrop;

                            Log.e(TAG,"title: " + title);

                            listItem listItem = new listItem(id,title,overview,realeseDate,image_url,cover);

                            listItems.add(listItem);
                            mutableLiveData.setValue(listItems);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.e(TAG,"empty body");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e(TAG,"no response");
            }
        });
    }
}
