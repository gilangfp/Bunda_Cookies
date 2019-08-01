package com.example.bunda_dewi_cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<GetData> list;
    private Adapter adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView search;
    String[] item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.prograss);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
        fetchContact("cies", "");

        // Inisialisasi SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handler digunakan untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Berhenti berputar/refreshing
                        swipeRefreshLayout.setRefreshing(false);

                        //Berganti Text Setelah Layar di Refresh
                        fetchContact("cies", "");

                    }
                },4000); //4000 millisecond = 4 detik
            }
        });


    }

    public void fetchContact(String type, String key){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<GetData>> call = apiInterface.getData(type, key);
        call.enqueue(new Callback<List<GetData>>() {
            @Override
            public void onResponse(Call<List<GetData>> call, Response<List<GetData>> response) {
                progressBar.setVisibility(View.GONE);
                list = response.body();
                adapter = new Adapter(list, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<GetData>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact("cies", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchContact("cies", newText);
                return false;
            }
        });
        return true;
    }

}
