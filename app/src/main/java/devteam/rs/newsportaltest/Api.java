package devteam.rs.newsportaltest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("get_all_news.php")
    Call<NewsList> getNewsList(@Query("category") int category);

    @GET("get_news_details.php")
    Call<NewsDetailList> getNewsDetailList(@Query("id") int id);

    @FormUrlEncoded
    @POST("add_news.php")
    Call<Message> uploadNews(@Field("title") String title,
                             @Field("subtitle") String subtitle,
                             @Field("text") String text,
                             @Field("id_author") String id_author,
                             @Field("category") String category,
                             @Field("url_img") String img_url
    );


}
