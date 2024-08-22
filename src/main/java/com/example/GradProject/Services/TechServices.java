package com.example.GradProject.Services;

import com.example.GradProject.Entites.Technician;
import com.example.GradProject.Repos.TechnicianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TechServices {
    @Autowired
    private TechnicianRepo technicianRepo;
    public List<Technician> getAllTechn() {

        return technicianRepo.findAll();
    }
}
