package com.articles.repo;

import com.articles.model.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepo extends JpaRepository<Requests, Long> {

}
