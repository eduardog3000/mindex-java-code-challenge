package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    
    @Autowired
    CompensationRepository compensationRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation for employee with id [{}]", compensation.getEmployee().getEmployeeId());
        
        Compensation existing = compensationRepository.findByEmployee_EmployeeId(compensation.getEmployee().getEmployeeId());
        if(existing != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Compensation already set for employee " + existing.getEmployee().getEmployeeId());
        }
        
        return compensationRepository.insert(compensation);
    }
    
    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation for employee with id [{}]", employeeId);
        
        return compensationRepository.findByEmployee_EmployeeId(employeeId);
    }
}
