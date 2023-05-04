package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingServiceImplTest {
    private String url;
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Before
    public void setup() {
        url = "http://localhost:" + port + "/reporting-structure?employeeId={id}";
    }
    
    @Test
    public void testReportingStructure() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        
        ReportingStructure reportingStructure = restTemplate
            .getForEntity(url, ReportingStructure.class, employeeId).getBody();
        
        assertNotNull(reportingStructure);
        assertNotNull(reportingStructure.getEmployee());
        assertEquals(reportingStructure.getEmployee().getEmployeeId(), employeeId);
        assertEquals(reportingStructure.getNumberOfReports(), 4);
    }
}
