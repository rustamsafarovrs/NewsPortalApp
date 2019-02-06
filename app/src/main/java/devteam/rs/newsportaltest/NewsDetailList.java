package devteam.rs.newsportaltest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsDetailList {

    @SerializedName("news")
    private List<NewsDetails> mNewsDetails;

    public List<NewsDetails> getNewsDetails() {
        return mNewsDetails;
    }

    public void setNewsDetails(List<NewsDetails> newsDetails) {
        mNewsDetails = newsDetails;
    }
}
