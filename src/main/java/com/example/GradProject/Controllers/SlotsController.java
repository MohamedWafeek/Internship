package com.example.GradProject.Controllers;

import com.example.GradProject.Entites.Slots;
import com.example.GradProject.Services.SlotsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("slots")
@CrossOrigin(origins = "*")
public class SlotsController
{
    @Autowired
    SlotsServices slotsServices;
    @GetMapping()
    public ResponseEntity<List<Slots>> getAllSlots(){
        return ResponseEntity.ok(slotsServices.getAllSLots());
    }
    @GetMapping("/availabeSlots/")
    public ResponseEntity<List<Slots>>getAllSlots(@RequestParam String sdate, @RequestParam Long sid){
        return ResponseEntity.ok(slotsServices.getAvailableSlots(sdate,sid));
    }

}
