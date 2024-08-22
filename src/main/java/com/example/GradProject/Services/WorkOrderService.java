package com.example.GradProject.Services;

import com.example.GradProject.DTOs.SlotsDTO;
import com.example.GradProject.DTOs.Work_OrderDTO;
import com.example.GradProject.Entites.Slots;
import com.example.GradProject.Entites.Technician;
import com.example.GradProject.Entites.Work_Order;
import com.example.GradProject.Repos.SlotsRepo;
import com.example.GradProject.Repos.TechnicianRepo;
import com.example.GradProject.Repos.WorkOrderRepo;
import com.example.GradProject.exceptions.BadDataException;
import com.example.GradProject.exceptions.EmployeeNotFoundException;
import com.example.GradProject.exceptions.InsufficientVacationBalanceException;
import com.example.GradProject.exceptions.MissingDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class WorkOrderService
{
    @Autowired
    private WorkOrderRepo workOrderRepo;
    @Autowired
    private TechnicianRepo  technicianRepo;
    @Autowired
    private SlotsRepo  slotsRepo;


    public List<Work_Order> getAllWork_Order() {

        return workOrderRepo.findAll();
    }

    public Work_Order getWorkById(Long id){
        return workOrderRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));

    }
    public List<Work_Order>getOPenWork(){
        return workOrderRepo.retrieveOpenWorkOrder();

    }
    public Work_Order createWorkOrder(Work_Order workOrder){
        validateOldDate(workOrder.getWoDate());
        return workOrderRepo.save(workOrder);


    }
   public ResponseEntity<?> updateWorkOrder(Work_Order workOrder){

       Work_Order workOrder1=workOrderRepo.findById(workOrder.getId()).orElseThrow(()->new EmployeeNotFoundException(workOrder.getId()));

     if (workOrder1.getStatus().equalsIgnoreCase("Cancelled"))
          throw new BadDataException("Order is Closed");

     if (workOrder1.getStatus().equalsIgnoreCase("Forse Close"))
            throw new BadDataException("Order is  Closed ");

     if (workOrderRepo.existsById(workOrder.getId()))
        {
            workOrder.setWoDate(workOrder1.getWoDate());
            workOrderRepo.save(workOrder);

        }

        else {
            throw new EmployeeNotFoundException(workOrder.getId());
             }

        return ResponseEntity.ok().build();

   }

  public  ResponseEntity<?> cancelWorkOrder (Long id){
      Work_Order workOrder1=workOrderRepo.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
      if(workOrder1.getStatus()!=null)
      {
          if(workOrder1.getStatus().equalsIgnoreCase("Cancelled")||workOrder1.getStatus().equalsIgnoreCase("Force Close "))
              throw new BadDataException("Order is already Cancelled");
      }

      workOrder1.setStatus("Cancelled");
      workOrderRepo.save(workOrder1);

      return ResponseEntity.ok().build();

  }
  public ResponseEntity<?>forceCloseWorkOrder(Long id){

        Work_Order workOrder1=workOrderRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
      if(workOrder1.getStatus()!=null)
      {
        if (workOrder1.getStatus().equalsIgnoreCase("Force Close ")|| workOrder1.getStatus().equalsIgnoreCase("Cancelled"))
            throw new BadDataException("Order is  already  Closed ");
      }
        workOrder1.setStatus("Force Close ");
        workOrderRepo.save(workOrder1);
      return ResponseEntity.ok().build();
  }
  public  void reAssignWorkOrder(Long wokOrderId,Long techId){
        assignValidation(techId, wokOrderId);
        validateAlreadyREAssigned(wokOrderId,techId);

       Work_Order  workOrder= workOrderRepo.findById(wokOrderId).orElseThrow(()->new MissingDataException("No Work Order Found With This Id"));
       technicianRepo.findById(techId).orElseThrow(()->new MissingDataException("No Tech Found With This Id"));
      if (workOrder.getStatus().equalsIgnoreCase("Force Close ")|| workOrder.getStatus().equalsIgnoreCase("Cancelled"))
          throw new BadDataException("Order is  already  Closed ");

      validateReassinSlot(workOrder.getWoDate(),techId,workOrder.getInterval());

       workOrderRepo.reAssignWorkOrder(wokOrderId,techId);

  }
    public void validateAlreadyREAssigned( Long id,Long techId)
    {
        Work_Order workOrder= workOrderRepo.findById(id).orElseThrow(()->new MissingDataException("ID is missing"));

        if(
              workOrder.getTechnician().getId()==techId

        )
        {
            throw  new BadDataException("Already Assigned");
        }


    }



  public void assignValidation  (Long techId,Long id ){
      if(id == null)
          throw new MissingDataException("id is missing");
      if(techId== null)
          throw new MissingDataException("tech id is missing");
  }

  public void reScheduleOrder(LocalDate date , Long id,String slot,Long techId){

      //  assignValidationid(id);
      Work_Order  workOrder=  workOrderRepo.findById(id).orElseThrow(()->new MissingDataException("No Work Order Found With This Id"));
      technicianRepo.findById(techId).orElseThrow(()->new MissingDataException("No Tech Found With This Id"));

      validateAlreadyAssigned(date, id, slot, techId);

      if (workOrder.getStatus().equalsIgnoreCase("Force Close ")|| workOrder.getStatus().equalsIgnoreCase("Cancelled"))
          throw new BadDataException("Order is  already  Closed ");


      validate14Days(id,date);
      validateSlot(date,techId,slot);
        workOrderRepo.reSchedule(date,id);
        workOrderRepo.updateSlot(slot,id);


  }
  public void validateAlreadyAssigned(LocalDate date , Long id,String slot,Long techId)
  {
     Work_Order workOrder= workOrderRepo.findById(id).orElseThrow(()->new MissingDataException("ID is missing"));
      Technician technician= technicianRepo.findById(techId).orElseThrow(()->new MissingDataException("ID is missing"));

      if( workOrder.getWoDate().equals(date)&&
            workOrder.getId().equals(id)&&
            workOrder.getInterval().equalsIgnoreCase(slot)&&
              technician.getId().equals(techId))
    {
        throw  new BadDataException("Already saved data");
    }


  }
  public void validateSlot(LocalDate date,Long id,String slot)
  {
     List<Long> intervalFound=workOrderRepo.findFreeSlots(id,date,slot);
     if(!intervalFound.isEmpty())
     {
         throw new BadDataException("Interval Is Not Available");
     }
  }

    public void validateReassinSlot(LocalDate date,Long id,String slot)
    {
        List<Long> intervalFound=workOrderRepo.findFreeSlots(id,date,slot);
        if(!intervalFound.isEmpty())
        {
            throw new BadDataException("Already Slot Assigned ");
        }
    }

    public  void assignValidationid (Long id){
        if (id==null)
            throw new MissingDataException("id is missing");

    }
//
    public void validate14Days(Long id,LocalDate date){
        Work_Order workOrder1 = workOrderRepo.findById(id).orElseThrow(()->new MissingDataException("No Work Order Found With This Id"));
        long validateDate=ChronoUnit.DAYS.between(workOrder1.getWoDate(), date)+1;
        if (validateDate>14 || validateDate <0){
            throw new InsufficientVacationBalanceException("Invalid");
        }
    }
    public void validateOldDate(LocalDate date){
          LocalDate currentDate=LocalDate.now();
         long validateDate=ChronoUnit.DAYS.between(currentDate,date);

         if(validateDate<0){

            throw new InsufficientVacationBalanceException("Invalid Old Date");
        }
    }


//    public List<Slots> getAvailableSlots(String sdate, Long sid) {
//        return slotsRepo.findAvailableSlotsForTech(sdate,sid);
//
//    }
}
