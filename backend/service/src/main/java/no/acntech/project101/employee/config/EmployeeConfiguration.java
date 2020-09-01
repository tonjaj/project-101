package no.acntech.project101.employee.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//TODO Add componentScan of employee package so that Spring can create beans
@Configuration
@ComponentScan(basePackages = "no.acntech.project101.employee")
public class EmployeeConfiguration {

}
