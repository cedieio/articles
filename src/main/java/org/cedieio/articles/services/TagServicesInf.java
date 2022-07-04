package org.cedieio.articles.services;

import org.cedieio.articles.models.Tag;

import java.time.LocalDate;

public interface TagServicesInf {

    public Tag getArticlesByTag(String tag);

    public Tag getArticlesByTagAndDate(String tag, LocalDate date);
}
