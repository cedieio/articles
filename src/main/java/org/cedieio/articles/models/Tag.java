package org.cedieio.articles.models;

import java.util.List;
import java.util.Set;

public class Tag {
    private String tag;
    private int count;
    private List<Long> articles;
    private Set<String> relatedTags;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Long> getArticles() {
        return articles;
    }

    public void setArticles(List<Long> articles) {
        this.articles = articles;
    }

    public Set<String> getRelatedTags() {
        return relatedTags;
    }

    public void setRelatedTags(Set<String> relatedTags) {
        this.relatedTags = relatedTags;
    }
}
