package com.sofka.contacts.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contact")
public class Contact {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "phone")
    private long phone;
    @Column(name = "hidden",nullable = false)
    private Boolean hidden;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "cnt_created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "cnt_updated_at")
    private Instant updatedAt;



}