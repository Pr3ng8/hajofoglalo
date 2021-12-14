package com.hajofoglalo.repositories;

import com.hajofoglalo.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
