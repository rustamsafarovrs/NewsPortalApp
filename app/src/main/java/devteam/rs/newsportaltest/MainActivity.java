package devteam.rs.newsportaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    NewsList mNewsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        mNewsList = new NewsList();

        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<NewsList> call = ((App) getApplication()).getApi().getNewsList();

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                mRecyclerView.setAdapter(new Adapter(MainActivity.this, response.body().getNewsList()));
                Log.i("NewsPortal", "onResponse");

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_LONG)
                        .show();
                Log.i("NewsPortal", "onFailure", t);
            }
        });

    }
}
