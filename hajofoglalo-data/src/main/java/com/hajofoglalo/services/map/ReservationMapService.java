package com.hajofoglalo.services.map;

import com.hajofoglalo.model.Reservation;
import com.hajofoglalo.services.ReservationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class ReservationMapService  extends AbstractMapService<Reservation, Long> implements ReservationService {
    @Override
    public Reservation findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Reservation> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Reservation object) {
        super.delete(object);
    }

    @Override
    public Reservation save(Reservation object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
