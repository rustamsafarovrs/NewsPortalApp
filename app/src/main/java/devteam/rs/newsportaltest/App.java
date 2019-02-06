package devteam.rs.newsportaltest;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private Api mApi;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://news-tj.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = mRetrofit.create(Api.class);
    }

    public Api getApi() {
        return mApi;
    }
}
