package rc.jpastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rc.jpastudent.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByName(String name);

    Student findByName(String name);
}
