package com.solstice.employee;

import com.solstice.employee.data.entities.Employee;
import com.solstice.employee.data.repository.EmployeeRepository;
import org.assertj.core.util.IterableUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    private static final Employee EMPLOYEE_TEMPLATE = new Employee(0 /* ignored */, "John", "Smith", 12345, "Chicago",
            "Technical Consultant", "jsmith@solstice.com", "file:///localhost/jsmith.jpg");

    @Autowired
    private EmployeeRepository repository;
    private Employee expectedEmployee;

    @Before
    public void setUp() {
        expectedEmployee = repository.save(EMPLOYEE_TEMPLATE);
    }

    @Test
    public void findWithValidIdShouldReturnEmployee() {
        final Optional<Employee> match = repository.findById(expectedEmployee.getId());

        assertTrue(match.isPresent());
        final Employee employee = match.get();
        assertEquals(expectedEmployee, employee);
    }

    @Test
    public void findWithInvalidIdShouldReturnEmpty() {
        final Optional<Employee> match = repository.findById(0);

        assertFalse(match.isPresent());
    }

    @Test
    public void findAllByIdsShouldReturnAllMatching() {
        Employee expectedEmployee2 = new Employee(0, "Hello", "World", 111, "NYC", "Analyst", "whatever@solstice.com"
                , null);
        expectedEmployee2 = repository.save(expectedEmployee2);

        final Iterable<Employee> matches = repository.findAllById(Arrays.asList(expectedEmployee.getId(),
                expectedEmployee2.getId()));

        final Collection actualEmployees = IterableUtil.toCollection(matches);
        assertEquals(2, actualEmployees.size());
        assertTrue(actualEmployees.contains(expectedEmployee));
        assertTrue(actualEmployees.contains(expectedEmployee2));
    }
}
