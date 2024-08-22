package com.example.GradProject.Controllers;

import com.example.GradProject.DTOs.SlotsDTO;
import com.example.GradProject.DTOs.Work_OrderDTO;
import com.example.GradProject.Entites.Slots;
import com.example.GradProject.Entites.Technician;
import com.example.GradProject.Entites.Work_Order;
import com.example.GradProject.Services.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("workOrder")
@CrossOrigin(origins = "*")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;
    @GetMapping
    public ResponseEntity<List<Work_Order>> getAllWorkOrder() {
        return ResponseEntity.ok(workOrderService.getAllWork_Order());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getWorkOrderById(@PathVariable Long id) {
        return ResponseEntity.ok( workOrderService.getWorkById(id));
    }
    @GetMapping("/open")
    public ResponseEntity<List<Work_Order>>getOpenedWork(){

        return ResponseEntity.ok(workOrderService.getOPenWork());
    }



    @PostMapping
    public ResponseEntity<?>createWorkOrder(@RequestBody Work_Order workOrder){
        Work_Order createWorkOrder = workOrderService.createWorkOrder(workOrder);
        return  ResponseEntity.ok(createWorkOrder);

    }
      @PutMapping()
      public ResponseEntity<?>updateWorkOrder(@RequestBody Work_Order workOrder){
          workOrderService.updateWorkOrder(workOrder);
          return  ResponseEntity.ok("Updated Successfully ");

      }
      @PutMapping("/cancelOrder/")
      public ResponseEntity<?>cancelWorkOrder(@RequestParam Long id){
        workOrderService.cancelWorkOrder(id);
          return  ResponseEntity.ok("Cancelled Successfully");
      }
    @PutMapping("/Forse Close/")
    public ResponseEntity<?>forceCloseWorkOrder(@RequestParam Long id){
        workOrderService.forceCloseWorkOrder(id);
        return  ResponseEntity.ok("forceClosed Successfully");
    }
    @PutMapping("/reassign/")
    public ResponseEntity<?>reAssign(@RequestParam Long wokOrderId,@RequestParam Long techId){
        workOrderService.reAssignWorkOrder(wokOrderId, techId);
        return  ResponseEntity.ok("Reassigned Successfully");


    }


    @PutMapping("/reSchedule/")
    public ResponseEntity<?>reSchedule(@RequestParam LocalDate date, @RequestParam Long id,@RequestParam String slot,@RequestParam Long techId){
        workOrderService.reScheduleOrder(date,id,slot,techId);
        return  ResponseEntity.ok("Reschedule Successfully");

    }

}
