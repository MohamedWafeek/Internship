package com.example.GradProject.Controllers;

import com.example.GradProject.Entites.Technician;
import com.example.GradProject.Services.TechServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tech")
@CrossOrigin(origins = "*")
public class TechController {
    @Autowired
    private TechServices techServices;
    @GetMapping("techn")
    public ResponseEntity<List<Technician>> getAllTechn(){
        return ResponseEntity.ok(techServices.getAllTechn());
    }

}
