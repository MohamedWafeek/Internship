package com.example.GradProject.Services;

import com.example.GradProject.Entites.Slots;
import com.example.GradProject.Repos.SlotsRepo;
import com.example.GradProject.exceptions.BadDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SlotsServices {
    @Autowired
    private SlotsRepo slotsRepo;
    public List<Slots> getAllSLots() {
        return slotsRepo.findAll();
    }


    public List<Slots> getAvailableSlots(String sdate, Long sid) {
        return slotsRepo.findAvailableSlotsForTech(sdate,sid);

    }


}


