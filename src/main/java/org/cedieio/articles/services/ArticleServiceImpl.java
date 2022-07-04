package org.cedieio.articles.services;

import org.cedieio.articles.models.Article;
import org.cedieio.articles.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("default")
public class ArticleServiceImpl implements ArticleServiceInf{

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);

        return article.isPresent() ? article.get() : null;
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
}
