package com.hajofoglalo.repositories;

import com.hajofoglalo.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositroy extends CrudRepository<Post, Integer> {
}
