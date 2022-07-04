package org.cedieio.articles.services;

import org.cedieio.articles.models.Article;
import org.cedieio.articles.models.Tag;
import org.cedieio.articles.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Qualifier("default")
public class TagsServicesImpl implements TagServicesInf {

    private final ArticleRepository articleRepository;

    public TagsServicesImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Tag getArticlesByTag(String tag) {
        List<Article> articles = articleRepository.findByTagsOrderByIdDesc(tag);

        Tag tagModel = new Tag();
        tagModel.setTag(tag);
        tagModel.setCount(articles.size());
        tagModel.setArticles(new ArrayList<>());
        tagModel.setRelatedTags(new HashSet<>());
        if(!articles.isEmpty()){
            for(Article article: articles){
                Set<String> articleTags = article.getTags().stream().filter(s -> !s.equalsIgnoreCase(tag)).collect(Collectors.toSet());
                tagModel.getRelatedTags().addAll(articleTags);
            }

            tagModel.setArticles(articles.stream().limit(10).map(a -> a.getId()).collect(Collectors.toList()));
        }

        return tagModel;
    }

    @Override
    public Tag getArticlesByTagAndDate(String tag, LocalDate date) {
        List<Article> articles = articleRepository.findByTagsAndDateOrderByIdDesc(tag, date);

        Tag tagModel = new Tag();
        tagModel.setTag(tag);
        tagModel.setCount(articles.size());
        tagModel.setArticles(new ArrayList<>());
        tagModel.setRelatedTags(new HashSet<>());

        if(!articles.isEmpty()){
            for(Article article: articles){
                Set<String> articleTags = article.getTags().stream().filter(s -> !s.equalsIgnoreCase(tag)).collect(Collectors.toSet());
                tagModel.getRelatedTags().addAll(articleTags);
            }

            tagModel.setArticles(articles.stream().limit(10).map(a -> a.getId()).collect(Collectors.toList()));
        }

        return tagModel;
    }
}
