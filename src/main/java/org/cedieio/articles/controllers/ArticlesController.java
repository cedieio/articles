package org.cedieio.articles.controllers;

import org.cedieio.articles.exceptions.ArticleNotFoundException;
import org.cedieio.articles.models.Article;
import org.cedieio.articles.repositories.ArticleRepository;
import org.cedieio.articles.services.ArticleServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticlesController {

    @Autowired
    @Qualifier("default")
    private ArticleServiceInf articleService;

    @GetMapping("/articles")
    public List<Article> getArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable Long id) {
        try {
            Article articleResult = articleService.getArticle(id);
            if (articleResult == null) {
                throw new ArticleNotFoundException("Article with id: " + id + " not found");
            }

            return articleResult;
        }catch(ArticleNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Article with id: " + id + " not found",
                    exception
            );
        }
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody Article article){
        Article returnArticle = articleService.createArticle(article);

        return returnArticle;
    }
}
