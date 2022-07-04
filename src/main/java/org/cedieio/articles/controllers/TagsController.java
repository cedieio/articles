package org.cedieio.articles.controllers;

import org.cedieio.articles.models.Article;
import org.cedieio.articles.models.Tag;
import org.cedieio.articles.repositories.ArticleRepository;
import org.cedieio.articles.services.TagServicesInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class TagsController {

    @Autowired
    @Qualifier("default")
    private TagServicesInf tagService;

    @GetMapping("/tags/{tag}")
    public Tag getArticlesByTag(@PathVariable String tag){
        return tagService.getArticlesByTag(tag);
    }

    @GetMapping("/tags/{tag}/{date}")
    public Tag getArticlesByTagAndDate(@PathVariable String tag, @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date){
       return tagService.getArticlesByTagAndDate(tag, date);
    }
}
