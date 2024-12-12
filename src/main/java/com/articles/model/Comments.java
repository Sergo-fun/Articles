package com.articles.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comments implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String username;
    private String date;

    @Column(length = 5000)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ads ad;

    public Comments(String username, String date, String text) {
        this.username = username;
        this.date = date;
        this.text = text;
    }
}
