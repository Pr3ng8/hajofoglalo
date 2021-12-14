package com.hajofoglalo.repositories;

import com.hajofoglalo.model.Boat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends CrudRepository<Boat, Long> {
}
