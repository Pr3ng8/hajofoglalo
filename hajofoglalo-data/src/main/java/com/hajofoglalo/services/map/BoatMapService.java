package com.hajofoglalo.services.map;

import com.hajofoglalo.model.Boat;
import com.hajofoglalo.services.BoatService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class BoatMapService  extends AbstractMapService<Boat, Long> implements BoatService {
    @Override
    public Boat findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Boat> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Boat object) {
        super.delete(object);
    }

    @Override
    public Boat save(Boat object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
