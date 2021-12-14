package com.hajofoglalo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boats")
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Boat extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "maximum_capacity")
    private int maximum_capacity;

    private boolean deleted = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boat")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
