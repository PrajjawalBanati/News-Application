package com.example.basicnewsapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicnewsapplication.api.ApiClient;
import com.example.basicnewsapplication.api.ApiInterface;
import com.example.basicnewsapplication.models.Articles;
import com.example.basicnewsapplication.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Articles> articles = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Adapters adapter;
    Button Buzzfeed,Cnn,Techcrunch,Time,TimesOfIndia,TheHindu,FoxSports,FoxNews;

    public static final String key = "84298221dc3f4d5ba1f496bd1052fa0e";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Buzzfeed=(Button) findViewById(R.id.buzzfeednews);
        Cnn=(Button)findViewById(R.id.cnnnews);
        Techcrunch=(Button)findViewById(R.id.techcrunchnews);
        Time=(Button)findViewById(R.id.timenews);
        TimesOfIndia=(Button)findViewById(R.id.thetimesofindianews);
        TheHindu=(Button)findViewById(R.id.thehindunews);
        FoxSports=(Button)findViewById(R.id.foxsports);
        FoxNews=(Button)findViewById(R.id.foxnews);
        Buzzfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("buzzfeed");
            }
        });
        Cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("cnn");
            }
        });
        Techcrunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("techcrunch");
            }
        });
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("time");
            }
        });
        TimesOfIndia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("the-times-of-india");
            }
        });
        TheHindu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("the-hindu");
            }
        });
        FoxSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("fox-sports");
            }
        });
        FoxNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJson("fox-news");
            }
        });

        LoadJson("cnn");
    }
    private void LoadJson(String source)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String sources=source;
        Call<News>call;
        call=apiInterface.getNews(sources,key);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                articles=response.body().getArticles();
                adapter= new Adapters(articles,MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }
}
