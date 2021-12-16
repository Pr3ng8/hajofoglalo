package com.hajofoglalo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE comments SET deleted = false WHERE id=?")
@Where(clause = "deleted=false")
public class Comment extends BaseEntity{
    @Column(name = "body")
    private String body;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
