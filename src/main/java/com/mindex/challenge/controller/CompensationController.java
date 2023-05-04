package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
    
    @Autowired
    CompensationService compensationService;
    
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for employee with id [{}]", compensation.getEmployee().getEmployeeId());
        
        return compensationService.create(compensation);
    }
    
    @GetMapping("/compensation")
    public Compensation read(@RequestParam String employeeId) {
        LOG.debug("Received compensation read request for employee with id [{}]", employeeId);
        
        return compensationService.read(employeeId);
    }
}
