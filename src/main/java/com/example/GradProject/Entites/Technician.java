package com.example.GradProject.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TECHNICAN_GROUP_A")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="T_ID")
    private Long id;



    @Column (name = "NAME")
    private String techName;



}
