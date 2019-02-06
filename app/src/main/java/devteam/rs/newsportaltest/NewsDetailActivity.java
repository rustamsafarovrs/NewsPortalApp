package devteam.rs.newsportaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

        Intent intent = getIntent();

        int newsId = Integer.parseInt(intent.getStringExtra(NEWS_ID));

        mTitleTextView = findViewById(R.id.news_detail_title);
        mSubtitleTextView = findViewById(R.id.news_detail_subtitle);
        mTextView = findViewById(R.id.news_detail_text);


        Call<NewsDetailList> call = ((App) getApplication()).getApi().getNewsDetailList(newsId);

        call.enqueue(new Callback<NewsDetailList>() {
            @Override
            public void onResponse(Call<NewsDetailList> call, Response<NewsDetailList> response) {
                mTitleTextView.setText(response.body().getNewsDetails().get(0).getTitle());
                mSubtitleTextView.setText(response.body().getNewsDetails().get(0).getSubtitle());
                mTextView.setText(response.body().getNewsDetails().get(0).getText());

                Log.i("NewsPortal", "onResponse");

            }

            @Override
            public void onFailure(Call<NewsDetailList> call, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, "An error occurred during networking", Toast.LENGTH_LONG)
                        .show();
                Log.i("NewsPortal", "onFailure", t);
            }
        });


    }
}
