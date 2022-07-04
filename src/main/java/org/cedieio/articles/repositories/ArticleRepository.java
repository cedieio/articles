package org.cedieio.articles.repositories;

import org.cedieio.articles.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a " +
            "left outer join a.tags " +
            "where upper(tags) = upper(:tag) " +
            "order by a.id desc")
    public List<Article> findByTagsOrderByIdDesc(@Param("tag") String tag);

    @Query("select a from Article a " +
            "left outer join a.tags " +
            "where upper(tags) = upper(:tag) and a.date = :date " +
            "order by a.id desc")
    public List<Article> findByTagsAndDateOrderByIdDesc(@Param("tag") String tag, @Param("date") LocalDate date);
}
