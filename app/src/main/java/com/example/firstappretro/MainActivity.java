package com.example.firstappretro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView txtView ;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView=findViewById(R.id.txtView);
        btnSubmit=findViewById(R.id.btnSubmit);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()) // (Gson) transfert les données (Json) en classe java
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        //Call<Post> call = apiInterface.getPos2();
        Call<Modele> call = apiInterface.getPost1();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.enqueue(new Callback<Modele>() {
                    @Override
                    public void onResponse(Call<Modele> call, Response<Modele> response) {
                        int useId = response.body().getUserId();
                        int id = response.body().getId();
                        String title = response.body().getTitle();
                        String body = response.body().getBody();
                        //txtView.setText("UseId : "+ useId +"\n"+ "Id : "+  id +"\n"+"Title : " +title +"\n"+"Body : "+ body);
                        txtView.setText(title);
                    }

                    @Override
                    public void onFailure(Call<Modele> call, Throwable t) {
                        txtView.setText(t.getMessage());
                    }
                });
            }
        });
        Button btn = findViewById(R.id.todos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}