package com.cms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    private Double totalExperience;
    private Double relevantExperience;
    
    private String primaryTechnology;
    private String secondaryTechnology;
    
    private String currentCompany;
    private Double currentSalary;
    private Double expectedSalary;
    private String noticePeriod;
    
    private LocalDate interviewDate;
    private String interviewTime;
    private String interviewMode;
    private String interviewerName;
    
    private String status; // NEW, SCHEDULED, SELECTED, REJECTED
    
    @Column(length = 1000)
    private String remarks;
}
