package com.curdTesting.testing.controller;
import com.curdTesting.testing.model.StudentModel;
import com.curdTesting.testing.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;
//    @Autowired because we do not want to create new Student
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
   @GetMapping//("/info")
    public List<StudentModel> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping("/add")
    public void createStudent(@RequestBody StudentModel studentModel){
        studentService.createStudent(studentModel);
    }


    @DeleteMapping("delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
            studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(
            @PathVariable("id")Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(id,name,email);
    }

}
