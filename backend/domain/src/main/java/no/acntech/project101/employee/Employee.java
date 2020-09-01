package no.acntech.project101.employee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {

    //TODO Create the enitity for Employee

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    //Constructor
    public Employee() {
        // Hibernate
    }

    public Employee(final Long id, final String firstName, final String secondName, final LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {return this.id; }
    public String getFirstName() {return this.firstName; }
    public void setFirstName(final String firstName) {this.firstName = firstName; }
    public String getSecondName() {return this.secondName; }
    public void setSecondName(final String secondName) {this.secondName = secondName; }
    public LocalDate getDateOfBirth() {return this.dateOfBirth; }
    public void setDateOfBirth(final LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth; }
    public Long getCompanyId() {return this.companyId; }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
