package com.solstice.employee.data.repositories;

import com.solstice.employee.data.entities.Employee;
import com.solstice.employee.data.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeEmptyRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

    @Test
    public void findWithSomeIdShouldReturnEmpty() {
        final Optional<Employee> match = repository.findById(1);
        assertFalse(match.isPresent());
    }

    @Test
    public void findAllByIdsShouldReturnEmpty() {
        final Iterable<Employee> matches = repository.findAllById(Arrays.asList(1, 2, 3));
        assertFalse(matches.iterator().hasNext());
    }
}
