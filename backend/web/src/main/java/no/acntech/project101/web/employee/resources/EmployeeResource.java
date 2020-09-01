package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;
import org.apache.catalina.webresources.TomcatJarInputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("employees")
//TODO This is a REST controler and should receive request on path employees
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;


    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    public EmployeeResource(final EmployeeService employeeService, final EmployeeDtoConverter employeeDtoConverter, final EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        //TODO create a GET endpoint find all employees in the database and return them
        //final EmployeeDto newEmployee1 = new EmployeeDto(1l, "Tonja", "Joseph", LocalDate.of(1996,8,21), 123l);
        //final EmployeeDto newEmployee2 = new EmployeeDto(2l, "Alexandra", "Vedeler", LocalDate.of(1995,4,4), 321l);

        //List employeeList = new ArrayList<EmployeeDto>();
        //employeeList.add(newEmployee1);
        //employeeList.add(newEmployee2);

        final List<Employee> employees = employeeService.findAll();
        final List<EmployeeDto> collect = employees.stream().map(employeeDtoConverter::convert).collect(Collectors.toList());

        return ResponseEntity.ok(collect);


    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        // TODO create a GET endpoint that fetches a spesific employee based on its ID
        //final EmployeeDto employee = new EmployeeDto(1l, "Tonja", "Joseph", LocalDate.of(1996,8,21), 123l);
        //return ResponseEntity.ok(employee);

        final Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            final EmployeeDto convert = employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(convert);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        //TODO Create a POST endpoint that accepts an employeeDTO and saves it in the database
        //final EmployeeDto newEmployee = new EmployeeDto(1, "Tonja", "Joseph", LocalDate.of(1996,08,21), 123);
        final Employee convert = employeeConverter.convert(employeeDto);
        final Employee saved = employeeService.save(convert);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        // TODO Create a DELETE endpoint that deletes a specific employee
        final Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()) {
            employeeService.delete(id);
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        //TODO Create a PATCH endpoint that updates an employee with new values
        final Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setSecondName(employeeDto.getLastName());
            existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());

            Employee saved = employeeService.save(existingEmployee);

            final EmployeeDto convert = employeeDtoConverter.convert(saved);
            return ResponseEntity.ok(convert);
        }
        else {
            return ResponseEntity.notFound().build();
        }

        //final EmployeeDto updatedEmployee = new EmployeeDto(1l, "Tonja", "Joseph", LocalDate.of(1996,8,21), 100l);
        //return ResponseEntity.ok(updatedEmployee);
    }
}
