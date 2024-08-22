package com.example.GradProject.Repos;

import com.example.GradProject.DTOs.SlotsDTO;
import com.example.GradProject.Entites.Slots;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotsRepo extends JpaRepository<Slots,String> {
    @Query(value = "SELECT  a.id , a.interval  \n" +   //select all interval
            "FROM BPM_EXECUTION.time_slots_group_a a\n" +
            "WHERE a.interval NOT IN (\n" +
            "    SELECT w.interval \n" +
            "    FROM BPM_EXECUTION.work_order_group_a w \n" +
            "    WHERE w.wo_date = TO_DATE(:p_date, 'YYYY-MM-DD')\n" +
            "      AND w.tech_id = :p_tech_id\n" +
            "      AND w.interval IS NOT NULL\n" +
            "      AND w.status not in ('Cancelled','Force Close')\n" +
            ")\n" +
            "ORDER BY a.id",nativeQuery = true)
    List<Slots> findAvailableSlotsForTech(String p_date, Long p_tech_id);


}
