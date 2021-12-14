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
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
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

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id",  nullable = false) },
            inverseJoinColumns =
                    { @JoinColumn(name = "role_id", referencedColumnName = "id",  nullable = false) })
    private Set<Role> role = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserStatus userStatus;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Boat boat;


    private void setUserStatus() { this.userStatus.setStatus(true); }
}
