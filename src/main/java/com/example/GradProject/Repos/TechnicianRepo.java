package com.example.GradProject.Repos;

import com.example.GradProject.Entites.Technician;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepo extends JpaRepository<Technician,Long> {

}
