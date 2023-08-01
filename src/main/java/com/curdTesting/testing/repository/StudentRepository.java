package com.curdTesting.testing.repository;

import com.curdTesting.testing.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository <StudentModel, Long>{
    //SELECT *from STUDENT where email=?JPQL not sql
    @Query("SELECT s FROM StudentModel s WHERE s.email=?1")
    Optional<StudentModel> findStudentByEmail(String email);

    boolean existsById(Long id);

}
