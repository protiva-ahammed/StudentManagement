package com.curdTesting.testing.repository;

import com.curdTesting.testing.model.StudentModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {
    
//    public List<StudentModel> getStudents(){
//        return studentRepository.findAll();
//    }
    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkfindStudentByEmail() {
        String email ="ahmed123@gmail.com"; //Student=model
        StudentModel student = new StudentModel(
                1L,"Ahmed123",email, LocalDate.of(1999, Month.JULY,14),"0114387156"

        );
        underTest.save(student);//h2-local-db is saving

        //when
        Optional<StudentModel> expected = underTest.findStudentByEmail(email);//expected is boolean bcz the project coding repository has boolean type

        //then
        assertThat(expected).isPresent();
        assertThat(expected.get().getEmail()).isEqualTo(email);
    }

    @Test//    boolean existsById(Long id);

    @Disabled
    void checkExistsById() {
        String email ="ahmed123@gmail.com"; //Student=model
        StudentModel student = new StudentModel(
                1L,"Ahmed123",email, LocalDate.of(1999, Month.JULY,14),"0114387156"

        );
        underTest.save(student);//saving the demo data then checking if it is working alright or not
        long id=1L;
        boolean expected= underTest.existsById(id);
        assertThat(expected).isTrue();

    }
}