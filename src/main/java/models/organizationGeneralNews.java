package models;

import java.util.Objects;

public class organizationGeneralNews {
    private int newsId;
    private String newsData;
    private String newsType;
    private int newsUserId;

    public organizationGeneralNews(String newsData, String newsType, int newsUserId) {
        this.newsData = newsData;
        this.newsType = newsType;
        this.newsUserId = newsUserId;
    }

    public int getNewsUserId() {
        return newsUserId;
    }

    public void setNewsUserId(int newsUserId) {
        this.newsUserId = newsUserId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        organizationGeneralNews that = (organizationGeneralNews) o;
        return newsId == that.newsId &&
                newsUserId == that.newsUserId &&
                newsData.equals(that.newsData) &&
                newsType.equals(that.newsType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, newsData, newsType, newsUserId);
    }
}