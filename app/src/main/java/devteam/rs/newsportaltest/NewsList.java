package devteam.rs.newsportaltest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsList {

    @SerializedName("news")
    private List<News> mNewsList;


    public List<News> getNewsList() {
        return mNewsList;
    }

    public void setNewsList(List<News> newsList) {
        mNewsList = newsList;
    }

}
