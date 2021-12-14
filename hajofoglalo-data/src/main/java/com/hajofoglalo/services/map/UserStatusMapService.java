package com.hajofoglalo.services.map;

import com.hajofoglalo.model.UserStatus;
import com.hajofoglalo.services.UserStatusService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class UserStatusMapService extends AbstractMapService<UserStatus, Long> implements UserStatusService {
    @Override
    public UserStatus findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<UserStatus> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(UserStatus object) {
        super.delete(object);
    }

    @Override
    public UserStatus save(UserStatus object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
