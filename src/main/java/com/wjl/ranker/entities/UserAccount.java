package com.wjl.ranker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class UserAccount {
    private static final String SEQUENCE_NAME = "user_account_sequence";

    @Id
    @SequenceGenerator(
            name = SEQUENCE_NAME,
            sequenceName = SEQUENCE_NAME,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SEQUENCE_NAME
    )
    private Long id;
    private String name;
    @OneToMany(mappedBy = "userAccount")
    private Set<Score> scoreSet;

    public UserAccount(String name) {
        this.setName(name);
    }
}
