package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String url;
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Before
    public void setup() {
        url = "http://localhost:" + port + "/compensation";
    }
    
    @Test
    public void testCreateRead() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        
        Compensation testCompensation = new Compensation();
        testCompensation.setSalary(100_000);
        testCompensation.setEmployee(testEmployee);
        testCompensation.setEffectiveDate(LocalDate.now());
        
        // Test create
        
        Compensation created = restTemplate.postForEntity(url, testCompensation, Compensation.class).getBody();
        
        assertCompensationEquals(testCompensation, created);
        
        // Test read
        Compensation read = restTemplate.getForEntity(url + "?employeeId={id}", Compensation.class , testEmployee.getEmployeeId()).getBody();
        
        assertCompensationEquals(testCompensation, read);
    }
    
    private void assertCompensationEquals(Compensation expected, Compensation actual) {
        assertNotNull(actual);
        assertNotNull(actual.getEmployee());
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary(), 0);
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
