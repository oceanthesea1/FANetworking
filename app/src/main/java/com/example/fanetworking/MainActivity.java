package com.example.fanetworking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataService service = retrofit.create(DataService.class);
        Call<DataResponse> callData = service.getData();
        callData.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    DataResponse dataResponse = response.body();
                    if (dataResponse != null) {
                        List<Team> teams = dataResponse.getTeams();
                        adapter = new MyAdapter(MainActivity.this, teams);
                        recyclerView.setAdapter(adapter);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}