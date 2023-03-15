package com.dea.management.app.employee.dto;

import com.dea.management.app.employee.EmployeeType;
import com.dea.management.app.employee.domain.Employee;
import com.dea.management.app.position.domain.Position;
import com.dea.management.app.position.dto.PositionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private String linkedin;
    private EmployeeType employeeType;
    @JsonProperty("position")
    private PositionDto positionDto;

    public String getName() {
        return name;
    }

    public static List<EmployeeDto> fromEmployees(List<Employee> students) {
        return students.stream().map(student -> {
            EmployeeDto studentDto = EmployeeDto.fromEmployee(student);
            return studentDto;
        }).collect(Collectors.toList());
    }

    public static EmployeeDto fromEmployee(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getUser().getName());
        employeeDto.setEmail(employee.getUser().getEmail());
        employeeDto.setLinkedin(employee.getUser().getLinkedin());
        employeeDto.setEmployeeType(employee.getEmployeeType());

        Position position = employee.getPosition();

        employeeDto.setPositionDto(new PositionDto(position.getId(), position.getDescription(), position.getSeniority()));

        return employeeDto;
    }

}
