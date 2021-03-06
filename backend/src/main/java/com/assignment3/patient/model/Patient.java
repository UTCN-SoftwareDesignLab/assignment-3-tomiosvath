package com.assignment3.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 8, nullable = false)
    private String idCardNb;

    @Column(length = 13, nullable = false)
    private String personalCode;

    private LocalDate birthDate;

    @Column(length = 512, nullable = false)
    private String address;
}
