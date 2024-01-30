package com.example.firstappretro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<Modele> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface postes = retrofit.create(ApiInterface.class);
        Call<List<Modele>> call = postes.getPostes();
        call.enqueue(new Callback<List<Modele>>() {
            @Override
            public void onResponse(Call<List<Modele>> call, Response<List<Modele>> response) {
                adapter.addAll(response.body());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Modele>> call, Throwable t) {
                TextView textView = findViewById(R.id.message);
                textView.setText(t.getMessage());
            }
        });
    }
}