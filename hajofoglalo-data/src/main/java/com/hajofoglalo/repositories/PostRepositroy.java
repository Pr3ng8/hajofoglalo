package com.hajofoglalo.repositories;

import com.hajofoglalo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositroy extends JpaRepository<Post, Long> {
}
