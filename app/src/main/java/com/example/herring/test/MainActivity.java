package com.example.herring.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mDescriptions = new ArrayList<>();
    private ArrayList<String> mPhotoUrls = new ArrayList<>();
    private ArrayList<String> mPhotoIDs = new ArrayList<>();

    private Toast toast;

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mDescriptions,mPhotoUrls, mPhotoIDs,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<PhotoModel>> call = api.getPhotos();

        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if (!response.isSuccessful()) {
                    toast = Toast.makeText(getApplicationContext(),"ErrorCode: " + response.code(), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    List<PhotoModel> photos = response.body();
                    int size = (photos.size() < 100) ? photos.size() : 100;
                    for (int i = 0; i < size; i++) {
                        if (photos.get(i) != null) {
                            mDescriptions.add(photos.get(i).getTitle());
                            mPhotoUrls.add(photos.get(i).getThumbnailUrl());
                            mPhotoIDs.add("ID: " + (i + 1));
                        }
                    }
                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                toast = Toast.makeText(getApplicationContext(),"Error: " + t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

}
