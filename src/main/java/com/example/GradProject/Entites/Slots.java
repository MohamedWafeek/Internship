package com.example.GradProject.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TIME_SLOTS_GROUP_A")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Slots {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "work_order_seq", sequenceName = "bpm_execution.work_order_seq", allocationSize = 1)
    @Column(name = "id",nullable = false)
    private  Long id ;


    @Column(name = "INTERVAL")
    private String interval;



}
