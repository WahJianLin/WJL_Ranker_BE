package com.wjl.ranker.entities.config;

import com.wjl.ranker.entities.RankingItem;
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
            RankingItem alpha = new RankingItem(
                    "alpha",
                    0);
            RankingItem beta = new RankingItem(
                    "beta",
                    0);
            RankingItem charlie = new RankingItem(
                    "charlie",
                    1);

            rankingItemRepo.saveAll(
                    List.of(alpha, beta, charlie)
            );
        };
    }
}
