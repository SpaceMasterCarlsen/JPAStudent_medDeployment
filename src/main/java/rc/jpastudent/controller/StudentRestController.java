package rc.jpastudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rc.jpastudent.model.Student;
import rc.jpastudent.repositories.StudentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentRestController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String detHerErRoden(){
        return "Du er i roden af JPAStudent";
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        var obj = studentRepository.findAll();
        return obj;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        Optional<Student> orgStudent = studentRepository.findById(id);
        if(orgStudent.isPresent()){
            return ResponseEntity.ok(orgStudent.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/name/{name}")
    public ResponseEntity<Student> getStudent(@PathVariable String name){
        Student student = studentRepository.findByName(name);
        if(student != null){
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Wrong naming convention for restfull api endpoints, should just be students.
    //because the get, post, put, patch, delete verbs already says what the endpoint should do.
    @GetMapping("/addStudent")
    public void addStudent(){
        Student obj = new Student();
        obj.setName("unNamed");
        obj.setBornDate(LocalDate.of(2000, 1, 1));
        obj.setBornTime(LocalTime.of(5, 30));
        studentRepository.save(obj);
    }

    //Corrected version of post students, what a json body being sendt, insteadt of java creating one
    //Also an overload method
    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student){
        System.out.println(student);
        return studentRepository.save(student);
    }

    //old endpoint
//    @PutMapping("/student")
//    @ResponseStatus(HttpStatus.OK)
//    public Student putStudent(@RequestBody Student student){
//        System.out.println(student);
//        return studentRepository.save(student);
//    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable int id, @RequestBody Student student){
        Optional<Student> orgStudent = studentRepository.findById(id);
        if(orgStudent.isPresent()){
                studentRepository.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/{name}")
    public List<Student> getStudentByName(@PathVariable String name){
        return studentRepository.findAllByName(name);
    }


    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        Optional<Student> orgStudent = studentRepository.findById(id);
        if(orgStudent.isPresent()){
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }
}
