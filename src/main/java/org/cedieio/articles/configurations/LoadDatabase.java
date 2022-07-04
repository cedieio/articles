package org.cedieio.articles.configurations;

import org.cedieio.articles.models.Article;
import org.cedieio.articles.repositories.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ArticleRepository repository) {
        return args -> {
            Article articleOne = new Article();
            articleOne.setTitle("Fish and chips!");
            articleOne.setBody("Fish and chips are good!");
            articleOne.setTags(Arrays.asList(new String[]{
                    "Health",
                    "Lunch",
                    "Dinner"
            }));
            articleOne.setDate(LocalDate.now());

            Article articleTwo = new Article();
            articleTwo.setTitle("Running is good!");
            articleTwo.setBody("Running is great exercise");
            articleTwo.setTags(Arrays.asList(new String[]{
                    "Health",
                    "Exercise",
                    "Cardio"
            }));

            articleTwo.setDate(LocalDate.now());

            log.info("Saving fish and chips article");
            repository.save(articleOne);
            log.info("Saving exercise article");
            repository.save(articleTwo);
        };
    }
}
