package com.curdTesting.testing.config;


import com.curdTesting.testing.model.StudentModel;
import com.curdTesting.testing.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args-> {
            StudentModel std1 = new StudentModel(
                    "Ahmed",
                    "ahmed@gmail.com",
                    LocalDate.of(1999, Month.JULY,14),
                    "1234678"
            );

            StudentModel std2 = new StudentModel(
                    "Ahmed2",
                    "ahmed2@gmail.com",
                    LocalDate.of(1999, Month.JULY,14),
                    "1234678"
            );
            StudentModel std3 = new StudentModel(
                    "Ahmed3",
                    "ahmed3@gmail.com",
                    LocalDate.of(1999, Month.JULY,14),
                    "1234678"
            );

            studentRepository.saveAll(
                    List.of(std1,std2,std3)
            );

        };
    }
}
