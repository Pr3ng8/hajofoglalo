package com.hajofoglalo.services.map;

import com.hajofoglalo.model.Post;
import com.hajofoglalo.services.PostService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PostMapService extends AbstractMapService<Post, Long> implements PostService {
    @Override
    public Post findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Post> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Post object) {
        super.delete(object);
    }

    @Override
    public Post save(Post object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
