package com.example.GradProject.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="WORK_ORDER_GROUP_A")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Work_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @SequenceGenerator(name = "work_order_seq", sequenceName = "bpm_execution.work_order_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name="TECH_ID")
    private Technician technician;
    @Column(name="CUSTOMER_NAME",nullable = false)
    private String customerName;
    @Column(name = "CUSTOMER_PHONE",nullable = false)
    private String customerPhone;
    @Column(name = "CUSTOME_MAIL")
    private String customerMail;
    @Column(name = "CUSTOMER_ADDRESS")
    private String customerAddress;
    @Column(name = "WO_DATE")
    private LocalDate woDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REACHSTATUS")
    private String reachStatus;
    @Column(name = "INTERVAL")
    private String interval;

//public Work_Order( Long id,Technician technician,String taskName,String customerName,String customerPhone,String customerMail,String customerAddress,String visitDate,String woDate,String status,String reachStatus)
//{
//    this.id=id;
//    this.technician=technician;
//    this.taskName=taskName;
//    this.customerName=customerName;
//    this.customerPhone=customerPhone;
//    this.customerMail=customerMail;
//    this.customerAddress=customerAddress;
//    this.visitDate=visitDate;
//    this.woDate=woDate;
//    this.status=status;
//    this.reachStatus=reachStatus;
//}

}
