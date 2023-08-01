package com.curdTesting.testing.service;

import com.curdTesting.testing.model.StudentModel;
import com.curdTesting.testing.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> getStudents(){
        return studentRepository.findAll();
    }

    public void createStudent(StudentModel studentModel) {
        Optional<StudentModel>studentModelOptional = studentRepository.findStudentByEmail(studentModel.getEmail());
        if(studentModelOptional.isPresent()){
            throw new IllegalStateException("Email has been registered!");
        }
        studentRepository.save(studentModel);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + id + " does not exists ");
        }

        studentRepository.deleteById(id);
    }

    @Transactional//this annotation make all go in managerial state for jpa to run handle
    //no need to code in  repository
    public void updateStudent(Long id, String name, String email) {
        StudentModel studentModel = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id " + id + " does not exist!"
                ));
        if (name !=null && name.length() > 0 && !Objects.equals(studentModel.getName(),name)){
            studentModel.setName(name);
        }

        if (email != null && email.length()>0 &&
        !Objects.equals(studentModel.getEmail(),email)){
            Optional<StudentModel> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email has been taken");
            }
            studentModel.setEmail(email);
        }
    }
}
