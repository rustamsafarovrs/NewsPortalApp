package devteam.rs.newsportaltest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewsActivity extends AppCompatActivity {

    EditText mTitleEditText;
    EditText mSubtitleEditText;
    EditText mEditText;
    EditText mIdAuthorEditText;
    EditText mCategoryEditText;
    EditText mUrlImgEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.add_news_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewsActivity.this.finish();
            }
        });

        mTitleEditText = findViewById(R.id.add_news_title_edit_text);
        mSubtitleEditText = findViewById(R.id.add_news_subtitle_edit_text);
        mEditText = findViewById(R.id.add_news_text_view);
        mIdAuthorEditText = findViewById(R.id.add_news_id_author_edit_text);
        mCategoryEditText = findViewById(R.id.add_news_category_edit_text);
        mUrlImgEditText = findViewById(R.id.add_news_url_img_edit_text);


        findViewById(R.id.add_news_publish_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadNews();
            }
        });



    }

    private void uploadNews() {
        Call<Message> call = ((App) getApplication()).getApi().uploadNews(
                mTitleEditText.getText().toString(),
                mSubtitleEditText.getText().toString(),
                mEditText.getText().toString(),
                mIdAuthorEditText.getText().toString(),
                mCategoryEditText.getText().toString(),
                mUrlImgEditText.getText().toString()
        );

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(@NonNull Call<Message> call, @NonNull Response<Message> response) {

                Snackbar.make(getWindow().getDecorView().getRootView(),
                        response.body().message, Snackbar.LENGTH_LONG)
                        .show();

                Log.i("NewsPortal", "onResponse");

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_LONG)
//                        .show();

                Snackbar.make(getWindow().getDecorView().getRootView(),
                        "NO INTERNET CONNECTIONS", Snackbar.LENGTH_LONG)
                        .show();


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
