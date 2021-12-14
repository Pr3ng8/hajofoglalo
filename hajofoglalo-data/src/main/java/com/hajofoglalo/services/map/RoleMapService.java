package com.hajofoglalo.services.map;


import com.hajofoglalo.model.Role;
import com.hajofoglalo.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class RoleMapService extends AbstractMapService<Role, Long> implements RoleService {
    @Override
    public Role findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Role> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Role object) {
        super.delete(object);
    }

    @Override
    public Role save(Role object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
