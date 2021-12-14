package com.hajofoglalo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userstatus")
public class UserStatus extends BaseEntity{

    @Column(name = "status")
    private boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
