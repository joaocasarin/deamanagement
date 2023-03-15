package com.dea.management.employee;

import com.dea.management.app.employee.EmployeeType;
import com.dea.management.app.employee.domain.Employee;
import com.dea.management.app.employee.repository.EmployeeRepository;
import com.dea.management.app.position.domain.Position;
import com.dea.management.app.position.repository.PositionRepository;
import com.dea.management.app.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeeTestUtils {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    public void createFakeEmployees(int amount) {

        Position position = Position.builder()
                .description("Dev")
                .seniority("Senior")
                .build();

        this.positionRepository.save(position);

        for (int i = 0; i < amount; i++) {
            User u = new User();
            u.setEmail("email " + i);
            u.setName("name " + i);
            u.setLinkedin("linkedin " + i);
            u.setPassword("password " + i);

            Employee employee = Employee.builder()
                    .employeeType(EmployeeType.DEVELOPER)
                    .user(u)
                    .position(position)
                    .build();

            this.employeeRepository.save(employee);

        }
    }

}