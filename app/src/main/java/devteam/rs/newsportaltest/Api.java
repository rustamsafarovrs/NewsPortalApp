package devteam.rs.newsportaltest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("get_all_news.php")
    Call<NewsList> getNewsList(@Query("category") int category);

    @GET("get_news_details.php")
    Call<NewsDetailList> getNewsDetailList(@Query("id") int id);

}
