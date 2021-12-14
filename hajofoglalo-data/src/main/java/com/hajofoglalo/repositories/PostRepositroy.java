package com.hajofoglalo.repositories;

import com.hajofoglalo.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepositroy extends CrudRepository<Post, Integer> {
}
