package com.wjl.ranker.entities.config;

import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.repositories.CategoryRepo;
import com.wjl.ranker.repositories.RankingItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EntityConfig {
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepo categoryRepo, RankingItemRepo rankingItemRepo) {
        return args -> {
            Category novel = new Category(
                    "novel",
                    "novel desc"
            );
            Category movie = new Category(
                    "movie",
                    "movie desc"
            );
            RankingItem alpha = new RankingItem(
                    "alpha",
                    null);
            RankingItem beta = new RankingItem(
                    "beta",
                    null);
            RankingItem charlie = new RankingItem(
                    "charlie",
                    null);

            rankingItemRepo.saveAll(
                    List.of(alpha, beta, charlie)
            );

            categoryRepo.saveAll(
                    List.of(novel, movie)
            );
        };
    }
}
