package com.hajofoglalo.services.map;

import com.hajofoglalo.model.User;
import com.hajofoglalo.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class UserMapService extends AbstractMapService<User, Long> implements UserService {

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<User> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(User object) {
        super.delete(object);
    }

    @Override
    public User save(User object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
