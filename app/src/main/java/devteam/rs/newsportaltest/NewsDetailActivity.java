package devteam.rs.newsportaltest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String NEWS_ID = "id";


    private TextView mTitleTextView;
    private TextView mSubtitleTextView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.news_detail_container).setVisibility(View.GONE);
        findViewById(R.id.progressbar).setVisibility(View.VISIBLE);

        Intent intent = getIntent();

        int newsId = Integer.parseInt(intent.getStringExtra(NEWS_ID));

        mTitleTextView = findViewById(R.id.news_detail_title);
        mSubtitleTextView = findViewById(R.id.news_detail_subtitle);
        mTextView = findViewById(R.id.news_detail_text);


        Call<NewsDetailList> call = ((App) getApplication()).getApi().getNewsDetailList(newsId);

        call.enqueue(new Callback<NewsDetailList>() {
            @Override
            public void onResponse(Call<NewsDetailList> call, Response<NewsDetailList> response) {

                Picasso.get()
                        .load(response.body().getNewsDetails().get(0).getUrlImage())
                        .into((ImageView) findViewById(R.id.news_detail_image_view));

                ((TextView)findViewById(R.id.news_detail_category))
                        .setText(response.body().getNewsDetails().get(0).getCategory());

                ((TextView)findViewById(R.id.news_detail_date))
                        .setText(response.body().getNewsDetails().get(0).getLastModifiedDate());

                ((TextView)findViewById(R.id.news_detail_author))
                        .setText(response.body().getNewsDetails().get(0).getAuthorName());

                mTitleTextView.setText(response.body().getNewsDetails().get(0).getTitle());
                mSubtitleTextView.setText(response.body().getNewsDetails().get(0).getSubtitle());
                mTextView.setText(response.body().getNewsDetails().get(0).getText());


                findViewById(R.id.progressbar).setVisibility(View.GONE);
                findViewById(R.id.news_detail_container).setVisibility(View.VISIBLE);

                Log.i("NewsPortal", "onResponse");

            }

            @Override
            public void onFailure(Call<NewsDetailList> call, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, "An error occurred during networking", Toast.LENGTH_LONG)
                        .show();

                findViewById(R.id.progressbar).setVisibility(View.GONE);
                findViewById(R.id.news_detail_container).setVisibility(View.GONE);

                Log.i("NewsPortal", "onFailure", t);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();

    }
}
