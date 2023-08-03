package com.curdTesting.testing.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
//table name will be same as class name
public class StudentModel {
    @Id
    @SequenceGenerator(
            name="curdstudent_sequence",
            sequenceName="curdstudent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "curdstudent_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;
    private String phn;
    public StudentModel() {
    }


    public StudentModel(Long id,
                        String name,
                        String email,
                        LocalDate dob,
                        String phn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phn = phn;
    }

    public StudentModel(
                        String name,
                        String email,
                        LocalDate dob,
                        String phn) {

        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phn = phn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public Integer getAge(){
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", phn='" + phn + '\'' +
                '}';
    }


}
