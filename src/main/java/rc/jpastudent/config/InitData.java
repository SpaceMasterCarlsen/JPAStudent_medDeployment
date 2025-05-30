package rc.jpastudent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rc.jpastudent.model.Student;
import rc.jpastudent.repositories.StudentRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception{
        Student bob = new Student();
        bob.setName("Bob");
        bob.setBornDate(LocalDate.of(2000, 1, 10));
        bob.setBornTime(LocalTime.of(3, 30));
        studentRepository.save(bob);

        Student jens = new Student();
        jens.setName("Jens");
        jens.setBornDate(LocalDate.of(2000, 1, 11));
        jens.setBornTime(LocalTime.of(3, 33));
        studentRepository.save(jens);
    }
}
