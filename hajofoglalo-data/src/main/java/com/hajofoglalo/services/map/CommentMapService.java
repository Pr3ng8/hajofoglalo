package com.hajofoglalo.services.map;

import com.hajofoglalo.model.Comment;
import com.hajofoglalo.services.CommentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class CommentMapService extends AbstractMapService<Comment, Long> implements CommentService {
    @Override
    public Comment findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Comment> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Comment object) {
        super.delete(object);
    }

    @Override
    public Comment save(Comment object) {
        if ( object != null ) return super.save(object);
        return null;
    }
}
