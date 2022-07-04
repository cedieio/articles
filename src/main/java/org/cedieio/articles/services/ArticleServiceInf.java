package org.cedieio.articles.services;

import org.cedieio.articles.models.Article;

import java.util.List;

public interface ArticleServiceInf {
    public List<Article> getAllArticles();

    public Article getArticle(Long id);

    public Article createArticle(Article article);
}
