package com.articles.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Requests implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String tel;

    @Column(length = 5000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ads ad;
    @OneToOne(fetch = FetchType.LAZY)
    private Users user;

    public Requests(Ads ad, Users user, String message, String tel) {
        this.ad = ad;
        this.user = user;
        this.message = message;
        this.tel = tel;
    }
}
