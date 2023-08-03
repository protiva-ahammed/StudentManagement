package com.curdTesting.testing.service;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import com.curdTesting.testing.model.StudentModel;
import com.curdTesting.testing.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;
    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);//if we have more mocks to perform then they will be
        //used from MockitoAnnotations
        underTest = new StudentService(studentRepository);
    }

    @Test
    void checkGetStudents() {
        //when
        underTest.getStudents();

        //then
        verify(studentRepository).findAll();

    }

    @Test
    void canCreateStudent() {

        //given
        StudentModel student = new StudentModel(
                "mim",
                "mim@gmail.com",
                LocalDate.of(1999, Month.FEBRUARY,14),
                "1234678"
        );
        //when
        underTest.createStudent(student);
        //then
        ArgumentCaptor<StudentModel>studentModelArgumentCaptor=
                ArgumentCaptor.forClass(StudentModel.class);
        verify(studentRepository).save(studentModelArgumentCaptor.capture());
        StudentModel capturedStudent = studentModelArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowEmailHasBeenRegistered()
    {
        // given
        // id=1;
        StudentModel student = new StudentModel(
                "lim",
                "lim@gmail.com",
                LocalDate.of(1999, Month.FEBRUARY,14),
                "1234678"
        );
      given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(student));

        assertThatThrownBy(()->underTest.createStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email has been registered!");
        verify(studentRepository,never()).save(any());
    }
    @Test
    void canDeleteStudent() {
        //given
        long id= 1;
        given(studentRepository.existsById(id)).willReturn(true);
        //when
        underTest.deleteStudent(id);
        //then
        verify(studentRepository).deleteById(id);
    }

    @Test
    void willThrowStudentNotFoundException(){
        long id = 1;
        given(studentRepository.existsById(id)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()->underTest.deleteStudent(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + id + " does not exists ");
        verify(studentRepository,never()).deleteById(any());
    }

}