package com.example.GradProject.Repos;

import com.example.GradProject.Entites.Slots;
import com.example.GradProject.Entites.Work_Order;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WorkOrderRepo  extends JpaRepository<Work_Order,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE WORK_ORDER_GROUP_A w SET w.tech_Id = :techId WHERE w.id=:id",nativeQuery = true)
    void reAssignWorkOrder(Long id ,Long techId);


    @Modifying
    @Transactional
//    @Query(value="select id,CUSTOMER_NAME,CUSTOMER_PHONE,CUSTOME_MAIL from WORK_ORDER_GROUP_A where STATUS not in ('Cancelled','Force Close ')",nativeQuery = true)
    @Query(value = "SELECT w FROM Work_Order w WHERE w.status NOT IN ('Cancelled', 'Force Close ')")

    List<Work_Order>retrieveOpenWorkOrder();


    @Modifying
    @Transactional
    @Query(value = "UPDATE WORK_ORDER_GROUP_A d SET d.wo_Date =:date WHERE d.id=:id",nativeQuery = true)
    void reSchedule(LocalDate date, Long id );
    @Query(value = "select id from WORK_ORDER_GROUP_A where TECH_ID=:id and WO_DATE=:date and Interval =:slot and STATUS not in ('Cancelled','Force Close ') ",nativeQuery = true)
    List<Long> findFreeSlots(Long id,LocalDate date,String slot);


    @Modifying
    @Transactional
    @Query(value="UPDATE WORK_ORDER_GROUP_A s SET s.Interval =:slot where s.id=:id",nativeQuery = true)
    void updateSlot(String slot,Long id );



}
