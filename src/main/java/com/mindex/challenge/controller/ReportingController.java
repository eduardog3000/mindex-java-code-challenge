package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private ReportingService reportingService;
    
    @GetMapping("/reporting-structure")
    public ReportingStructure get(@RequestParam String employeeId) {
        LOG.debug("Received employee reporting structure request for id [{}]", employeeId);
        
        return reportingService.generateReportingStructure(employeeService.read(employeeId));
    }
}
