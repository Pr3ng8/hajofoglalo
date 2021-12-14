package com.hajofoglalo.repositories;

import com.hajofoglalo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
