package com.wjl.ranker.entities.config;

import com.wjl.ranker.entities.RankingItemEntity;
import com.wjl.ranker.repositories.RankingItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EntityConfig {
    @Bean
    CommandLineRunner commandLineRunner(RankingItemRepo rankingItemRepo) {
        return args -> {
            RankingItemEntity alpha = new RankingItemEntity(
                    "alpha",
                    0);
            RankingItemEntity beta = new RankingItemEntity(
                    "beta",
                    0);
            RankingItemEntity charlie = new RankingItemEntity(
                    "charlie",
                    1);

            rankingItemRepo.saveAll(
                    List.of(alpha, beta, charlie)
            );
        };
    }
}
