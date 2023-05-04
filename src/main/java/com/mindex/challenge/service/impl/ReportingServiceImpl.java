package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingServiceImpl implements ReportingService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingServiceImpl.class);
    
    @Autowired
    EmployeeService employeeService;
    
    @Override
    public ReportingStructure generateReportingStructure(Employee employee) {
        LOG.debug("Generating ReportingStructure for employee [{}]", employee);
        
        return new ReportingStructure(employee, countTotalReports(employee));
    }
    
    private int countTotalReports(Employee employee) {
        LOG.debug("Counting total reports for employee [{}]", employee);
        
        if(employee.getDirectReports() == null) return 0;
        
        int total = employee.getDirectReports().size();
        for(Employee directReport : employee.getDirectReports()) {
            directReport = employeeService.read(directReport.getEmployeeId());
            total += countTotalReports(directReport);
        }
        return total;
    }
}
