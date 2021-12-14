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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Reservation extends BaseEntity {

    @Column(name = "number_of_Passengers")
    private int number_of_Passengers;

    @Column(name = "start_of_rent")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDate start_of_rent;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "end_of_rent")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDate end_of_rent;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
