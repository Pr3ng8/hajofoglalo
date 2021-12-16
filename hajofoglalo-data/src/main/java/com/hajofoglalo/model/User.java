package com.hajofoglalo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = false WHERE id=?")
@Where(clause = "deleted=false")
public class User extends BaseEntity  {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "neptunkod")
    private String neptunkod;

    @Column(name = "bithdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDate bithdate;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(name = "user_roles",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id",  nullable = false) },
            inverseJoinColumns =
                    { @JoinColumn(name = "role_id", referencedColumnName = "id",  nullable = false) })
    private Set<Role> role = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY ,  mappedBy = "user")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.EAGER , mappedBy = "user")
    private UserStatus userStatus;

    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.EAGER ,  mappedBy = "user")
    private Boat boat;

    public List<String> getRoles() {
        List<String> tmp = new ArrayList<>();
        for (Role role : this.role) {
                tmp.add(role.getName());
        }
        return tmp;
    }
}
