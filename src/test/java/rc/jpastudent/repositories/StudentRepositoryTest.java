package rc.jpastudent.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import rc.jpastudent.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void setup(){
        Student fillWithOneStudent = new Student();
        fillWithOneStudent.setName("Test");
        studentRepository.save(fillWithOneStudent);
    }

    @Test
    void testOneBobExits(){
        List<Student> listOfStudents = studentRepository.findAllByName("Test");
        assertEquals(1, listOfStudents.size());
        assertEquals("Test", listOfStudents.get(0).getName());
    }
}